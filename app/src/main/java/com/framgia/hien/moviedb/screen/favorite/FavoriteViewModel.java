package com.framgia.hien.moviedb.screen.favorite;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.screen.ItemMovieNavigator;

import java.util.List;

public class FavoriteViewModel extends BaseObservable implements FavoriteAdapter.ItemMovieListener,
        SwipeRefreshLayout.OnRefreshListener {

    private MovieRepository mRepository;
    private FavoriteAdapter mAdapter;
    private ItemMovieNavigator mNavigator;
    public ObservableBoolean isRefresh;

    public FavoriteViewModel(Context context, MovieRepository repository, FavoriteAdapter adapter,
                             ItemMovieNavigator navigator) {
        mRepository = repository;
        mAdapter = adapter;
        mNavigator = navigator;
        isRefresh = new ObservableBoolean();
        mAdapter.setItemMovieListener(this);
    }

    @Override
    public void onItemMovieClick(Movie movie, int position, boolean isFavoriteClick) {
        if (isFavoriteClick) {
            mAdapter.removeMovie(movie, position);
        } else {
            mNavigator.onOpenMovieDetail(movie);
        }
    }

    @Override
    public void onRefresh() {
        getMovies();
        isRefresh.set(false);
        notifyChange();
    }

    public void onStart() {
        getMovies();
    }

    public FavoriteAdapter getAdapter() {
        return mAdapter;
    }

    public int getColorRefreshLayout() {
        return R.color.color_accent;
    }

    public boolean getIsRefresh() {
        return isRefresh.get();
    }

    private void getMovies() {
        List<Movie> movies = mRepository.getMovies();
        mAdapter.setMovies(movies);
    }
}
