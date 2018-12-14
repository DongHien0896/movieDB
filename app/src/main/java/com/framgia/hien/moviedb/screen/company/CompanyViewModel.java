package com.framgia.hien.moviedb.screen.company;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dong.moviedb.BuildConfig;
import com.framgia.hien.moviedb.data.model.Company;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.CompanyRepository;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.screen.BaseViewModel;
import com.framgia.hien.moviedb.screen.detail.DetailActivity;
import com.framgia.hien.moviedb.screen.home.MovieAdapter;
import com.framgia.hien.moviedb.util.Constants;
import com.framgia.hien.moviedb.util.rx.BaseScheduleProvider;
import com.framgia.hien.moviedb.util.rx.ScheduleProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CompanyViewModel extends BaseViewModel implements MovieAdapter.ItemClickListener {

    private AppCompatActivity mActivity;
    private int mCompanyId;
    private ProgressBar mProgressBar;
    private CompanyRepository mCompanyRepository;
    private MovieRepository mMovieRepository;
    public ObservableField<Company> companyObservableField = new ObservableField<>();
    private BaseScheduleProvider mBaseScheduleProvider;
    private CompositeDisposable mCompositeDisposable;
    private BackPressListener mBackPress;
    private MovieAdapter mMovieAdapter;

    public CompanyViewModel(AppCompatActivity activity, int companyId) {
        this.mActivity = activity;
        this.mCompanyId = companyId;
        setComponent();
    }

    private void setComponent() {
        this.mBaseScheduleProvider = ScheduleProvider.getInstance();
        mCompositeDisposable = new CompositeDisposable();
        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setItemClickListener(this);
    }

    public void setBackPress(BackPressListener listener) {
        mBackPress = listener;
    }

    public void onBackClicked(View view) {
        mBackPress.backPress();
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void setRepository(CompanyRepository repository, MovieRepository movieRepository) {
        this.mCompanyRepository = repository;
        this.mMovieRepository = movieRepository;
    }

    public MovieAdapter getMovieAdapter() {
        return this.mMovieAdapter;
    }

    public void getCompanyDetail() {
        mProgressBar.setVisibility(View.VISIBLE);
        Disposable disposable = mCompanyRepository.getCompanyDetail(mCompanyId, BuildConfig.API_KEY)
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<Company>() {
                    @Override
                    public void accept(Company company) throws Exception {
                        companyObservableField.set(company);
                        getMovieByCompany(company.getId());
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void getMovieByCompany(int companyId) {
        Disposable disposable = mMovieRepository.getAllMovieByCompany(companyId, BuildConfig.API_KEY, Constants.LANGUAGE)
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mMovieAdapter.setMovies(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onStart() {
        getCompanyDetail();
    }

    @Override
    protected void onStop() {

    }

    @Override
    public void onItemClicked(int movieId) {
        if (movieId != Constants.VALUE_INTEGER_NULL) {
            mActivity.startActivity(getMovieIntent(mActivity.getApplicationContext(), movieId));
        } else {
        }
    }

    public static Intent getMovieIntent(Context context, int movieId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constants.ARGUMENT_MOVIE_ID, movieId);
        return intent;
    }

    interface BackPressListener {
        void backPress();
    }
}
