package com.framgia.hien.moviedb.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.source.MovieDataSource;
import com.framgia.hien.moviedb.data.source.local.sqlite.DbHelper;
import com.framgia.hien.moviedb.data.source.local.sqlite.MovieDao;
import com.framgia.hien.moviedb.data.source.local.sqlite.MovieDaoImpl;

import java.util.List;

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {

    private static MovieLocalDataSource sInstance;
    private MovieDao mMovieDao;

    private MovieLocalDataSource(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    public static synchronized MovieLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new MovieLocalDataSource(
                    MovieDaoImpl.getInstance(DbHelper.getInstance(context)));
        }
        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void addMovie(Movie movie) {
        mMovieDao.insertMovie(movie);
    }

    @Override
    public void removeMovie(Movie movie) {
        mMovieDao.deleteMovie(movie);
    }

    @Override
    public boolean isExistMovie(Movie movie) {
        return mMovieDao.getMovie(movie.getId()) != null;
    }

    @Override
    public List<Movie> getMovies() {
        return mMovieDao.getMovies();
    }

    @Override
    public Movie getMovie(int movieId) {
        return mMovieDao.getMovie(movieId);
    }
}
