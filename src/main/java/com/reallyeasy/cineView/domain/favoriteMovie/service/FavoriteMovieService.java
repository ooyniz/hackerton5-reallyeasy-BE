package com.reallyeasy.cineView.domain.favoriteMovie.service;

import com.reallyeasy.cineView.domain.favoriteMovie.entity.FavoriteMovie;
import com.reallyeasy.cineView.domain.favoriteMovie.repository.FavoriteMovieRepository;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteMovieService {
    private final UserRepository userRepository;
//    private final MovieRepository movieRepository; // todo
    private final FavoriteMovieRepository favoriteMovieRepository;

    @Transactional
    public void toggleFavorite(Long movieId, Long userId) {
        // todo movie
//        Movie movie = movieRepository.findById(movieId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

//        Optional<FavoriteMovie> favoriteMovie = favoriteMovieRepository.findByMovieAndUser(movie, user);
        Optional<FavoriteMovie> favoriteMovie = favoriteMovieRepository.findByMovieIdAndUserId(movieId, userId);

        if (favoriteMovie.isPresent()) {
            favoriteMovieRepository.delete(favoriteMovie.get());
        } else {
//            FavoriteMovie newFavoriteMovie = new FavoriteMovie(movieId, user);
            FavoriteMovie newFavoriteMovie = new FavoriteMovie(movieId, user);
            favoriteMovieRepository.save(newFavoriteMovie);
        }
    }

    public void getFavoriteMovies(Long userId) {
        // List<MovieResponse> favoriteMovies = movieRepository.findFavoriteMoviesByUserId(userId);
//        @Query("SELECT fm.movie FROM FavoriteMovie fm WHERE fm.user.id = :userId")
//        List<Movie> findFavoriteMoviesByUserId(@Param("userId") Long userId);
        // todo return MovieResponse
    }
}
