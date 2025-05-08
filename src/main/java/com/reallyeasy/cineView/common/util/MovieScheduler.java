package com.reallyeasy.cineView.common.util;

import com.reallyeasy.cineView.domain.movie.dto.request.MovieRequest;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieSearchResponse;
import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieScheduler {
    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tmdb.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        log.info("=== 애플리케이션 시작: 영화 데이터 초기화 ===");
        updateMoviesDaily();
    }

    @Scheduled(cron = "0 0 2 * * *", zone = "Asia/Seoul") // 매일 새벽 2시 실행
    public void updateMoviesDaily() {
        log.info("=== 영화 데이터 업데이트 시작 ===");

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey + "&language=ko-KR&page=1";

        int page = 1;
        int maxPages = 3; // 예: 3페이지까지만 가져옴

        while (page <= maxPages) {
            String pagedUrl = url + "&page=" + page;
            ResponseEntity<MovieSearchResponse> response = restTemplate.getForEntity(pagedUrl, MovieSearchResponse.class);

            if (response.getBody() == null || response.getBody().getResults() == null) {
                log.warn("TMDB 응답이 비어있습니다. 종료합니다.");
                break;
            }

            for (MovieRequest dto : response.getBody().getResults()) {
                if (dto == null) continue;

                Optional<Movie> existingMovie = movieRepository.findByTmdbId(dto.getId());

                Movie movie;
                if (existingMovie.isPresent()) {
                    movie = existingMovie.get();
                    movie.updateFromRequest(dto);
                } else {
                    movie = dto.toEntity();
                }
                movieRepository.save(movie);
            }

            page++;
        }

        log.info("=== 영화 데이터 업데이트 완료 ===");
    }

}
