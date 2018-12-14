package com.framgia.hien.moviedb.screen.detail;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.hien.moviedb.data.model.Cast;

import io.reactivex.annotations.NonNull;

public class ItemCastViewModel extends BaseObservable {

    public ObservableField<Cast> observable = new ObservableField<>();
    private CastAdapter.ItemCastClickListener mItemClickListener;

    public ItemCastViewModel(CastAdapter.ItemCastClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setObservable(@NonNull Cast cast) {
        this.observable.set(cast);
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null || observable.get() == null) {
            return;
        }
        mItemClickListener.onItemCastClick(observable.get().getId());
    }
}
