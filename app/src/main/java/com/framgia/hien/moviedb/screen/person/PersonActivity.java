package com.framgia.hien.moviedb.screen.person;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ActivityPersonBinding;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.repository.PersonRepository;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;
import com.framgia.hien.moviedb.data.source.remote.PersonRemoteDataSource;
import com.framgia.hien.moviedb.util.Constants;

public class PersonActivity extends AppCompatActivity implements PersonViewModel.BackPressListener {

    private PersonViewModel mViewModel;
    ActivityPersonBinding mBinding;
    private TextView mTextOverview;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_person);
        setViewModel();
        mBinding.setViewModel(mViewModel);
    }

    private void setViewModel() {
        PersonRepository personRepository = new PersonRepository(PersonRemoteDataSource.getInstance());
        MovieRepository movieRepository = new MovieRepository(MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance(this));
        int personId = getIntent().getIntExtra(Constants.PERSON, 0);
        mViewModel = new PersonViewModel(this, personId, personRepository);
        initView();
        mViewModel.setTextView(mTextOverview);
        mViewModel.setProgressBar(mProgressBar);
        mViewModel.setBackPress(this);
        mViewModel.setRepository(movieRepository);
    }

    private void initView() {
        mTextOverview = mBinding.textPersonBiography;
        mProgressBar = mBinding.progressPersonIndicator;
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
}
