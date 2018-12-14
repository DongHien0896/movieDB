package com.framgia.hien.moviedb.screen.home;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.util.Constants;

import io.reactivex.annotations.NonNull;

public class ItemMovieViewModel extends BaseObservable {

    public ObservableField<Movie> movieObservableField = new ObservableField<>();
    private MovieAdapter.ItemClickListener mItemClickListener;

    public ItemMovieViewModel(MovieAdapter.ItemClickListener itemClick){
        this.mItemClickListener = itemClick;
    }

    public void setMovie(@NonNull Movie movie){
        movieObservableField.set(movie);
    }

    public void onItemClicked(View view){
        if (mItemClickListener == null || movieObservableField.get() == null){
            return;
        }
        mItemClickListener.onItemClicked(movieObservableField.get().getId());
    }
}
