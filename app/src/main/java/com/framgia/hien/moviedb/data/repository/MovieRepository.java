package com.framgia.hien.moviedb.data.repository;

import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.model.ResultMovie;
import com.framgia.hien.moviedb.data.source.MovieDataSource;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

public class MovieRepository implements MovieDataSource.RemoteDataSource, MovieDataSource.LocalDataSource{

    private MovieRemoteDataSource mRemoteDataSource;
    private MovieDataSource.LocalDataSource mLocalDataSource;
    private static MovieRepository sInstance;

    public MovieRepository(@NonNull MovieRemoteDataSource remoteDataSource,
                           MovieDataSource.LocalDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static synchronized MovieRepository getInstance(MovieRemoteDataSource movieRemoteDataSource,
                                                           MovieLocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(movieRemoteDataSource, localDataSource);
        }
        return sInstance;
    }

    @Override
    public Maybe<List<Movie>> getAllMovieByType(String key, int page, String type) {
        return mRemoteDataSource.getAllMovieByType(key, page, type);
    }

    @Override
    public Maybe<Movie> getDetailMovie(int movieId, String key) {
        return mRemoteDataSource.getDetailMovie(movieId, key);
    }

    @Override
    public Maybe<List<ResultMovie>> getAllMovieByPerson(String key, String language, String query) {
        return mRemoteDataSource.getAllMovieByPerson(key, language, query);
    }

    @Override
    public Maybe<List<Movie>> getAllMovieByCompany(int companyId, String key, String language) {
        return mRemoteDataSource.getAllMovieByCompany(companyId, key, language);
    }

    @Override
    public Maybe<List<Movie>> searchMovieByGenre(String key, int genreId, int page) {
        return mRemoteDataSource.searchMovieByGenre(key, genreId, page);
    }

    @Override
    public Maybe<List<Movie>> searchMovieByName(String key, String query, int page) {
        return mRemoteDataSource.searchMovieByName(key, query, page);
    }

    @Override
    public void addMovie(Movie movie) {
        mLocalDataSource.addMovie(movie);
    }

    @Override
    public void removeMovie(Movie movie) {
        mLocalDataSource.removeMovie(movie);
    }

    @Override
    public boolean isExistMovie(Movie movie) {
        return mLocalDataSource.isExistMovie(movie);
    }

    @Override
    public List<Movie> getMovies() {
        return mLocalDataSource.getMovies();
    }

    @Override
    public Movie getMovie(int movieId) {
        return mLocalDataSource.getMovie(movieId);
    }
}
