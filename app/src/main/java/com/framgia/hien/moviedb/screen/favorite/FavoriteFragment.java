package com.framgia.hien.moviedb.screen.favorite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.FragmentFavoriteBinding;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;
import com.framgia.hien.moviedb.screen.BaseFragment;
import com.framgia.hien.moviedb.screen.ItemMovieNavigator;

public class FavoriteFragment extends BaseFragment {

    private FavoriteViewModel mViewModel;
    private ItemMovieNavigator mItemMovieNavigator;

    public static FavoriteFragment getsInstance() {
        return new FavoriteFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ItemMovieNavigator) {
            mItemMovieNavigator = (ItemMovieNavigator) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFavoriteBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);
        MovieRepository repository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance(getContext()));
        FavoriteAdapter adapter = new FavoriteAdapter(getContext());
        adapter.setRepository(repository);
        mViewModel = new FavoriteViewModel(getContext(), repository, adapter, mItemMovieNavigator);
        mViewModel.onStart();
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mItemMovieNavigator = null;
        super.onDestroy();
    }
}
