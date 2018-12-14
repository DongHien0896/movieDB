package com.framgia.hien.moviedb.screen.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dong.moviedb.BuildConfig;
import com.framgia.hien.moviedb.data.model.Genre;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.screen.BaseViewModel;
import com.framgia.hien.moviedb.screen.detail.DetailActivity;
import com.framgia.hien.moviedb.util.Constants;
import com.framgia.hien.moviedb.util.rx.BaseScheduleProvider;
import com.framgia.hien.moviedb.util.rx.ScheduleProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SearchViewModel extends BaseViewModel implements SearchMovieAdapter.ItemClickListener {

    private Context mContext;
    private MovieRepository mMovieRepository;
    private ProgressBar mProgressBar;
    private BaseScheduleProvider mBaseScheduleProvider;
    private SearchMovieAdapter mMovieAdapter;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public SearchViewModel(Context context, MovieRepository movieRepository) {
        this.mContext = context;
        this.mMovieRepository = movieRepository;
        setComponent();
    }

    private void setComponent() {
        mMovieAdapter = new SearchMovieAdapter();
        mMovieAdapter.setItemClickListener(this);
        this.mBaseScheduleProvider = ScheduleProvider.getInstance();
    }

    public SearchMovieAdapter getMovieAdapter() {
        return mMovieAdapter;
    }

    public void setGenre(Genre genre) {
        getMovieByGenre(genre);
    }

    public void setQuery(String query) {
        getMovieByQuery(query);
    }

    private void getMovieByQuery(String query) {
        Disposable disposable = mMovieRepository.searchMovieByName(BuildConfig.API_KEY.toString()
                , query, Constants.PAGE_REQUEST)
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mProgressBar.setVisibility(View.GONE);
                        // set adapter for recyclerView.
                        mMovieAdapter.setMovies(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // show error
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void getMovieByGenre(Genre genre) {
        Disposable disposable = mMovieRepository.searchMovieByGenre(BuildConfig.API_KEY.toString()
                , genre.getId(), Constants.PAGE_REQUEST)
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mProgressBar.setVisibility(View.GONE);
                        // set adapter for recyclerView.
                        mMovieAdapter.setMovies(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // show error
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onStart() {
    }

    @Override
    protected void onStop() {
    }

    @Override
    public void onItemClicked(int movieId) {
        if (movieId == Constants.VALUE_INTEGER_NULL) {
            return;
        }
        mContext.startActivity(getMovieIntent(mContext, movieId));
    }

    public static Intent getMovieIntent(Context context, int movieId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ARGUMENT_MOVIE_ID, movieId);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtras(bundle);
        return intent;
    }
}
