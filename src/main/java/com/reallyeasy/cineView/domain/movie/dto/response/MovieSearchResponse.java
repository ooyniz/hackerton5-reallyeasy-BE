package com.reallyeasy.cineView.domain.movie.dto.response;

import com.reallyeasy.cineView.domain.movie.dto.MovieDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class MovieSearchResponse {
    private int page;
    private List<MovieDto> results;
    @Getter
    private int total_pages;

}
