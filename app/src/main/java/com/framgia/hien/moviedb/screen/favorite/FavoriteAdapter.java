package com.framgia.hien.moviedb.screen.favorite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemMovieFavoriteBinding;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder> {

    private List<Movie> mMovies;
    private LayoutInflater mInflater;
    private ItemMovieListener mItemMovieListener;
    private MovieRepository mRepository;
    private ImageButton mFavoriteIcon;

    public FavoriteAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieFavoriteBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_movie_favorite, parent, false);
        return new ItemViewHolder(binding, mItemMovieListener, mFavoriteIcon, mRepository);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void setMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void removeMovie(Movie movie, int position) {
        mMovies.remove(movie);
        notifyItemRemoved(position);
    }

    public void setItemMovieListener(ItemMovieListener listener) {
        mItemMovieListener = listener;
    }

    public void setRepository(MovieRepository repository) {
        mRepository = repository;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements FavoriteListener {

        private ItemMovieFavoriteBinding mBinding;
        private ItemFavoriteViewModel mViewModel;
        private FavoriteListener mFavoriteListener;
        private ItemMovieListener mItemMovieListener;

        public ItemViewHolder(ItemMovieFavoriteBinding binding, ItemMovieListener itemMovieListener,
                              ImageButton mImageIcon,
                              MovieRepository repository) {
            super(binding.getRoot());
            mBinding = binding;
            mFavoriteListener = this;
            mItemMovieListener = itemMovieListener;
            mViewModel = new ItemFavoriteViewModel(repository, mItemMovieListener, mFavoriteListener, mImageIcon);
            mBinding.setViewModel(mViewModel);
        }

        @Override
        public void onFavoriteClick(Movie movie) {
            mViewModel.removeFavorite(movie);
            mItemMovieListener.onItemMovieClick(movie, getAdapterPosition(), true);
        }

        private void bind(Movie movie) {
            mViewModel.setMovie(movie);
            mViewModel.setIconFavorite();
            mBinding.executePendingBindings();
        }
    }

    public interface ItemMovieListener {
        void onItemMovieClick(Movie movie, int position, boolean isFavoriteClick);
    }

    public interface FavoriteListener {
        void onFavoriteClick(Movie movie);
    }
}
