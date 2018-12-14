package com.framgia.hien.moviedb.screen.play;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.data.model.Trailer;

import io.reactivex.annotations.NonNull;

public class ItemTrailerViewModel extends BaseObservable {

    public ObservableField<Trailer> observable = new ObservableField<>();
    private TrailerAdapter.ItemTrailerClickListener mItemClickListener;

    public ItemTrailerViewModel(TrailerAdapter.ItemTrailerClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setObservable(@NonNull Trailer trailer) {
        observable.set(trailer);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || observable.get() == null) {
            return;
        }
        mItemClickListener.onItemTrailerClick(observable.get().getKey());
        view.setBackgroundColor(view.getResources().getColor(R.color.color_light_gray));
    }
}
