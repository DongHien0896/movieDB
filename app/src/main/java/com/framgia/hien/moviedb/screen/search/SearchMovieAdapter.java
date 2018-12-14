package com.framgia.hien.moviedb.screen.search;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemSearchMovieBinding;
import com.framgia.hien.moviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies;
    private ItemClickListener mItemClick;

    public SearchMovieAdapter() {
        mMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_search_movie, parent, false);
        return new ItemViewHolder(binding, mItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClick = itemClickListener;
    }

    public void setMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemSearchMovieBinding mBinding;
        private ItemSearchMovieViewModel mViewModel;

        public ItemViewHolder(ItemSearchMovieBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemSearchMovieViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        public void bind(Movie movie) {
            mViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onItemClicked(int movieId);
    }
}
