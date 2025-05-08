package com.reallyeasy.cineView.domain.movie.dto.response;

import com.reallyeasy.cineView.domain.movie.dto.request.MovieRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieSearchResponse {
    private int page;
    private List<MovieRequest> results;
    private int totalPages;
}
