package com.framgia.hien.moviedb.screen.play;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ActivityPlayBinding;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.model.Trailer;
import com.framgia.hien.moviedb.data.repository.TrailerRepository;
import com.framgia.hien.moviedb.data.source.remote.TrailerRemoteDataSource;
import com.framgia.hien.moviedb.util.Constants;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class PlayActivity extends YouTubeBaseActivity implements PlayViewModel.InterfaceBackClickListener {

    private ActivityPlayBinding mPlayBinding;
    private PlayViewModel mPlayViewModel;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayBinding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        initView();
        setViewModel();
        mPlayBinding.setViewModel(mPlayViewModel);
    }

    private void initView() {
        mProgressBar = mPlayBinding.progressTrailer;
    }

    private void setViewModel() {
        Movie movie = (Movie) getIntent().getSerializableExtra(Constants.MOVIE);
        TrailerRepository repository = TrailerRepository.getInstance(TrailerRemoteDataSource.getInstance());
        mPlayViewModel = new PlayViewModel(movie, this);
        mPlayViewModel.setTrailerRepository(repository);
        mPlayViewModel.setProgressBar(mProgressBar);
    }

    @Override
    public void onBackImageClick() {
        onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPlayViewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
