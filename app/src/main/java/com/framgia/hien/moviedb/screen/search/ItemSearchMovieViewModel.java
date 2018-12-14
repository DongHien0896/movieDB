package com.framgia.hien.moviedb.screen.search;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.hien.moviedb.data.model.Movie;

import io.reactivex.annotations.NonNull;

public class ItemSearchMovieViewModel extends BaseObservable {

    public ObservableField<Movie> movieObservableField = new ObservableField<>();
    private SearchMovieAdapter.ItemClickListener mItemClickListener;

    public ItemSearchMovieViewModel(SearchMovieAdapter.ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setMovie(@NonNull Movie movie) {
        this.movieObservableField.set(movie);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || movieObservableField.get() == null) {
            return;
        }
        mItemClickListener.onItemClicked(movieObservableField.get().getId());
    }
}
