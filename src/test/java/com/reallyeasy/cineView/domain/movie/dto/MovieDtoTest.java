package com.reallyeasy.cineView.domain.movie.dto;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieSearchResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@Transactional
class MovieDtoTest {
    @Autowired
    private MovieRepository movieRepository;

    private final String API_URL = "https://api.themoviedb.org/3/search/movie?api_key=102f26e3e34c2973885ba4f0b5a2d8ea&query=광해&language=ko-KR";

//    private final MovieDto movieDto = new MovieDto();
    @Test
    void toEntity() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieSearchResponse> response = restTemplate.getForEntity(API_URL, MovieSearchResponse.class);
        System.out.println(response.getBody());
        List<MovieDto> movies = response.getBody().getResults();
        System.out.println(movies);
        for (MovieDto movieDto : movies) {
            if(movieDto == null){
                continue;
            }
            Movie saved = movieRepository.save(movieDto.toEntity());
            System.out.println("saved");
        }
    }
}