package com.framgia.hien.moviedb.screen.favorite;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageButton;

import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.MovieRepository;

public class ItemFavoriteViewModel extends BaseObservable {

    public ObservableField<Movie> movieObservableField = new ObservableField<>();
    private FavoriteAdapter.ItemMovieListener mItemMovieListener;
    private FavoriteAdapter.FavoriteListener mFavoriteListener;
    private MovieRepository mRepository;
    private ImageButton mFavorite;

    public ItemFavoriteViewModel(MovieRepository repository,
                                 FavoriteAdapter.ItemMovieListener itemMovieListener,
                                 FavoriteAdapter.FavoriteListener favoriteListener,
                                 ImageButton imageButton) {
        mRepository = repository;
        mItemMovieListener = itemMovieListener;
        mFavoriteListener = favoriteListener;
        this.mFavorite = imageButton;
    }

    public void setMovie(Movie movie) {
        movieObservableField.set(movie);
    }

    public void onItemClick(View view) {
        if (mItemMovieListener == null || movieObservableField.get() == null) {
            return;
        }
        mItemMovieListener.onItemMovieClick(movieObservableField.get(), 0, false);
    }

    public void onFavoriteClick(View view) {
        mFavoriteListener.onFavoriteClick(movieObservableField.get());
    }

    public void setIconFavorite() {
        mFavorite.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        notifyChange();
    }

    public void removeFavorite(Movie movie) {
        mRepository.removeMovie(movie);
    }
}
