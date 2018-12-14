package com.framgia.hien.moviedb.data.source.local.sqlite;

import com.framgia.hien.moviedb.data.model.Movie;

import java.util.List;

public interface MovieDao {

    Movie getMovie(int movieId);

    List<Movie> getMovies();

    void insertMovie(Movie movie);

    void deleteMovie(Movie movie);
}
