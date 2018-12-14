package com.framgia.hien.moviedb.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemGenreBinding;
import com.framgia.hien.moviedb.data.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ItemViewHolder> {

    private List<Genre> mGenres;
    private ItemClickListener mListener;

    public GenresAdapter(){
        mGenres = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_genre, parent, false);
        return new ItemViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mListener = itemClickListener;
    }

    public void setGenres(List<Genre> genres){
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreBinding mBinding;
        private ItemGenreViewModel mItemView;
        public ItemViewHolder(ItemGenreBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            this.mBinding = binding;
            mItemView = new ItemGenreViewModel(listener);
            mBinding.setViewModel(mItemView);
        }

        private void bind(Genre genre) {
            mItemView.setGenre(genre);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onItemGenreClicked(Genre genre);
    }
}
