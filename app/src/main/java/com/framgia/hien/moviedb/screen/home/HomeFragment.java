package com.framgia.hien.moviedb.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.FragmentHomeBinding;
import com.framgia.hien.moviedb.data.repository.GenreRepository;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.GenreRemoteDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;
import com.framgia.hien.moviedb.screen.BaseFragment;
import com.framgia.hien.moviedb.util.rx.BaseScheduleProvider;
import com.framgia.hien.moviedb.util.rx.ScheduleProvider;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding mBinding;
    private static HomeFragment sInstance;
    private HomeFragmentViewModel mViewModel;
    private ViewPager mViewPager;
    private ProgressBar mProgressBar;

    public HomeFragment() {

    }

    public static HomeFragment getsInstance() {
        if (sInstance == null) {
            sInstance = new HomeFragment();
        }
        return sInstance;
    }

    public void setClickGenreItem(HomeFragmentViewModel.OnClickSearchMoviesByGenre clickGenreItem){
        mViewModel.setOnClickItemGenres(clickGenreItem);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mBinding.setBinding(this);
        MovieRepository repository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance(getActivity()));
        GenreRepository genreRepository = GenreRepository.getsInstance(GenreRemoteDataSource.getInstance());
        mViewModel = new HomeFragmentViewModel(getActivity().getSupportFragmentManager(), repository
                , genreRepository, getContext());
        mViewModel.setSchedulerProvider(ScheduleProvider.getInstance());
        mBinding.setFragmentViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSlider();
        initView();
        mViewModel.setProgressBar(mProgressBar);
    }

    private void initView() {
        mProgressBar = mBinding.progressIndicator;
    }

    private void setupSlider() {
        mViewPager = mBinding.viewpagerSlider;
        mBinding.tablayoutSlider.setupWithViewPager(mViewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
        mViewModel.startSliderInterval(mViewPager);
    }

    @Override
    public void onStop() {
        mViewModel.stopSliderInterval();
        mViewModel.onStop();
        super.onStop();
    }
}
