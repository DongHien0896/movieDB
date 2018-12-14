package com.framgia.hien.moviedb.screen.play;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemTrailerRecyclerBinding;
import com.framgia.hien.moviedb.data.model.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ItemViewHolder> {

    private List<Trailer> mTrailers;
    private ItemTrailerClickListener mClickListener;

    public TrailerAdapter() {
        mTrailers = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTrailerRecyclerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_trailer_recycler, parent, false);
        return new ItemViewHolder(binding, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mTrailers.get(position));
    }

    @Override
    public int getItemCount() {
        return mTrailers == null ? 0 : mTrailers.size();
    }

    public void setItemTrailerClick(ItemTrailerClickListener listener) {
        this.mClickListener = listener;
    }

    public void setTrailers(List<Trailer> trailers) {
        mTrailers.clear();
        mTrailers.addAll(trailers);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemTrailerRecyclerBinding mBinding;
        private ItemTrailerViewModel mViewModel;

        public ItemViewHolder(ItemTrailerRecyclerBinding binding, ItemTrailerClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemTrailerViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        public void bind(Trailer trailer) {
            mViewModel.setObservable(trailer);
            mBinding.executePendingBindings();
        }
    }

    interface ItemTrailerClickListener {
        void onItemTrailerClick(String trailerKey);
    }
}
