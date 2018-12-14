package com.framgia.hien.moviedb.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemMovieRecyclerBinding;
import com.framgia.hien.moviedb.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies;
    private ItemClickListener mItemClick;

    public MovieAdapter() {
        mMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieRecyclerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_movie_recycler, parent, false);
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

        private ItemMovieRecyclerBinding mBinding;
        private ItemMovieViewModel mItemMovie;

        public ItemViewHolder(ItemMovieRecyclerBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemMovie = new ItemMovieViewModel(listener);
            mBinding.setViewModel(mItemMovie);
        }

        private void bind(Movie movie) {
            mItemMovie.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onItemClicked(int movieId);
    }
}
