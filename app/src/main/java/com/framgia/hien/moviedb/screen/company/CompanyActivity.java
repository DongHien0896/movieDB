package com.framgia.hien.moviedb.screen.company;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ActivityCompanyBinding;
import com.framgia.hien.moviedb.data.repository.CompanyRepository;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.source.local.MovieLocalDataSource;
import com.framgia.hien.moviedb.data.source.remote.CompanyRemoteDataSource;
import com.framgia.hien.moviedb.data.source.remote.MovieRemoteDataSource;
import com.framgia.hien.moviedb.util.Constants;

public class CompanyActivity extends AppCompatActivity implements CompanyViewModel.BackPressListener {

    private CompanyViewModel mViewModel;
    private ActivityCompanyBinding mBinding;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_company);
        initView();
        setViewModel();
        mBinding.setViewModel(mViewModel);
    }

    private void initView() {
        mProgressBar = mBinding.progressCompanyIndicator;
    }

    private void setViewModel() {
        CompanyRepository companyRepository = new CompanyRepository(CompanyRemoteDataSource.getInstance());
        MovieRepository movieRepository = new MovieRepository(MovieRemoteDataSource.getInstance(),
                                                                MovieLocalDataSource.getInstance(this));
        int companyId = getIntent().getIntExtra(Constants.COMPANY, Constants.DEFAULT_VALUE);
        mViewModel = new CompanyViewModel(this, companyId);
        mViewModel.setProgressBar(mProgressBar);
        mViewModel.setRepository(companyRepository, movieRepository);
        mViewModel.setBackPress(this);
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
