package com.framgia.hien.moviedb.screen.home;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.hien.moviedb.data.model.Genre;

import io.reactivex.annotations.NonNull;

public class ItemGenreViewModel extends BaseObservable {
    public ObservableField<Genre> genreObservableField = new ObservableField<>();
    private GenresAdapter.ItemClickListener mItemClickListener;

    public ItemGenreViewModel(GenresAdapter.ItemClickListener mItemClick){
        this.mItemClickListener = mItemClick;
    }

    public void setGenre(@NonNull Genre genre){
        genreObservableField.set(genre);
    }

    public void onItemClicked(View view){
        if (mItemClickListener == null || genreObservableField.get() == null){
            return;
        }
        mItemClickListener.onItemGenreClicked(genreObservableField.get());
    }
}
