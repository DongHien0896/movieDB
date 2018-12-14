package com.framgia.hien.moviedb.screen.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.FragmentSearchBinding;
import com.framgia.hien.moviedb.data.model.Genre;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;
import com.framgia.hien.moviedb.screen.BaseFragment;

public class SearchFragment extends BaseFragment {

    private FragmentSearchBinding mBinding;
    private SearchViewModel mViewModel;
    private ProgressBar mProgressBar;
    private MovieRepository mMovieRepository;
    private Genre mGenre;

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance(getActivity()));
        mViewModel = new SearchViewModel(getContext(), mMovieRepository);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniView();
        if (mGenre != null) {
            mViewModel.setGenre(mGenre);
        }
        mViewModel.setProgressBar(mProgressBar);
    }

    private void iniView() {
        mProgressBar = mBinding.progressIndicator;
    }

    public void searchForResult(String query) {
        mViewModel.setQuery(query);
    }

    public void setGenre(Genre genre) {
        this.mGenre = genre;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
