package com.framgia.hien.moviedb.screen.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ActivityDetailBinding;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.CastRepository;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.CastRemoteDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;
import com.framgia.hien.moviedb.screen.BaseActivity;
import com.framgia.hien.moviedb.screen.favorite.FavoriteAdapter;
import com.framgia.hien.moviedb.util.Constants;

public class DetailActivity extends BaseActivity implements DetailViewModel.BackPressListener,
                                                            FavoriteAdapter.FavoriteListener {

    private ActivityDetailBinding mBinding;
    private DetailViewModel mViewModel;
    private ProgressBar mProgressBar;
    private TextView mTextOverview;
    private ImageView mImageDrop;
    private ImageView mImageTrailer;
    private ImageButton mFavoriteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        initView();
        setComponent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MovieRepository movieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(this));
        CastRepository castRepository = CastRepository.getsInstance(CastRemoteDataSource.getInstance());
        int movieId = intent.getIntExtra(Constants.ARGUMENT_MOVIE_ID, Constants.DEFAULT_VALUE);
        setViewModel(movieRepository, castRepository, movieId);
        mBinding.setViewModel(mViewModel);
        mViewModel.onStart();
    }

    private void setComponent() {
        MovieRepository movieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance(),
                                                                        MovieLocalDataSource.getInstance(this));
        CastRepository castRepository = CastRepository.getsInstance(CastRemoteDataSource.getInstance());
        Intent intent = getIntent();
        int movieId = intent.getIntExtra(Constants.ARGUMENT_MOVIE_ID, Constants.DEFAULT_VALUE);
        setViewModel(movieRepository, castRepository, movieId);
        mBinding.setViewModel(mViewModel);
    }

    private void setViewModel(MovieRepository movieRepository, CastRepository castRepository, int movieId) {
        mViewModel = new DetailViewModel(this, movieId, this, this);
        mViewModel.setRepository(movieRepository, castRepository);
        mViewModel.setProgressBar(mProgressBar);
        mViewModel.setTextView(mTextOverview);
        mViewModel.setImagView(mImageDrop, mImageTrailer);
        mViewModel.setImageButton(mFavoriteIcon);
    }

    private void initView() {
        mProgressBar = mBinding.progressDetailIndicator;
        mTextOverview = mBinding.textDetailOverview;
        mImageDrop = mBinding.imageDetailBackdrop;
        mImageTrailer = mBinding.imageTrailer;
        mFavoriteIcon = mBinding.buttonFavorite;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public void backPress() {
        onBackPressed();
    }

    @Override
    public void onFavoriteClick(Movie movie) {
        if (!mViewModel.checkFavorite(movie)) {
            addMovie(movie);
            showToast(Constants.MESSAGE_ADD_FAVORITE);
        } else {
            removeMovie(movie);
            showToast(Constants.MESSAGE_REMOVE_FAVORITE);
        }
        mBinding.setViewModel(mViewModel);
    }

    private void removeMovie(Movie movie) {
        mViewModel.updateResId(false);
        mViewModel.removeMovie(movie);
    }

    private void addMovie(Movie movie) {
        mViewModel.updateResId(true);
        mViewModel.addMovie(movie);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
