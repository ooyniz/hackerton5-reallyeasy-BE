package com.reallyeasy.cineView.domain.movie.service;

import com.reallyeasy.cineView.domain.movie.dto.MovieDto;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieSearchResponse;
import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;;

    private final JdbcTemplate jdbcTemplate;

    @Value("${tmdb.api.key}")
    private String API_KEY;
    private final String API_URL = "https://api.themoviedb.org/3/discover/movie?api_key=102f26e3e34c2973885ba4f0b5a2d8ea&language=ko-Kr&page=1";

    public MovieService(MovieRepository movieRepository, JdbcTemplate jdbcTemplate) {
        this.movieRepository = movieRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("TRUNCATE TABLE movie");

        int page = 1;
        int totalPages = 1;
        RestTemplate restTemplate = new RestTemplate();
        do {
            String url = String.format("%s?api_key=%s&language=ko-KR&page=%d", "https://api.themoviedb.org/3/discover/movie", API_KEY, page);
            ResponseEntity<MovieSearchResponse> response = restTemplate.getForEntity(url, MovieSearchResponse.class);

            if (response.getBody() == null || response.getBody().getResults() == null) break;



            // 저장
            try {
                for (MovieDto dto : response.getBody().getResults()) {
                    if (dto != null) {
                        movieRepository.save(dto.toEntity());
//                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalPages = response.getBody().getTotal_pages(); // 총 페이지 수
            page++;
        } while (page <= 3);
    }


    @Scheduled(cron = "0 */5 * * * * ", zone="Asia/Seoul")
    public void crawlMovies() {
        jdbcTemplate.execute("TRUNCATE TABLE movie");
        RestTemplate restTemplate = new RestTemplate();
        int page = 1;
        int totalPages = 1;
        do {
            ResponseEntity<MovieSearchResponse> response = restTemplate.getForEntity("https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY + "&language=ko-Kr", MovieSearchResponse.class);

            try {
                for (MovieDto movieDto : response.getBody().getResults()) {
//                    if(!movieRepository.existsByMovieId(movieDto.getId())) continue;
                    Movie movie = movieRepository.save(movieDto.toEntity());
                    System.out.println("saved");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalPages = 1;
            System.out.println("page is" + page);
            System.out.println("totalpages is " + totalPages);
            page++;
        }while (page <= totalPages);
    }
}
