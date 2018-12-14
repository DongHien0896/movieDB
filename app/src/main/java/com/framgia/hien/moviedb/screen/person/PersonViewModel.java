package com.framgia.hien.moviedb.screen.person;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dong.moviedb.BuildConfig;
import com.framgia.hien.moviedb.data.model.Person;
import com.framgia.hien.moviedb.data.model.ResultMovie;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.data.repository.PersonRepository;
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

public class PersonViewModel extends BaseViewModel implements MovieAdapter.ItemClickListener {

    private static final int OVEVERVIEW_MAXLINE = 30;
    private static final int OVEVERVIEW_MINLINE = 3;
    private AppCompatActivity mActivity;
    private PersonRepository mPersonRepository;
    public ObservableField<Person> personObservableField = new ObservableField<>();
    private BaseScheduleProvider mBaseScheduleProvider;
    private CompositeDisposable mCompositeDisposable;
    private int mIdPerson;
    private ProgressBar mProgressBar;
    private boolean mIsTextOverviewExpanded;
    private TextView mTextOverview;
    private BackPressListener mBackPress;
    private MovieRepository mMovieRepository;
    private MovieAdapter mMovieAdapter;

    public PersonViewModel(AppCompatActivity activity, int idPerson, PersonRepository repository) {
        this.mActivity = activity;
        this.mIdPerson = idPerson;
        this.mPersonRepository = repository;
        setComponent();
    }

    public void setRepository(MovieRepository movieRepository) {
        this.mMovieRepository = movieRepository;
        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setItemClickListener(this);
    }

    public MovieAdapter getMovieAdapter() {
        return mMovieAdapter;
    }

    private void setComponent() {
        this.mBaseScheduleProvider = ScheduleProvider.getInstance();
        mCompositeDisposable = new CompositeDisposable();
    }


    public void setBackPress(BackPressListener listener) {
        mBackPress = listener;
    }

    public void onBackClicked(View view) {
        mBackPress.backPress();
    }

    public void setTextView(TextView textView) {
        this.mTextOverview = textView;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void onTextClicked(View view) {
        if (mIsTextOverviewExpanded) {
            mTextOverview.setMaxLines(OVEVERVIEW_MINLINE);
        } else {
            mTextOverview.setMaxLines(OVEVERVIEW_MAXLINE);
        }
        mIsTextOverviewExpanded = !mIsTextOverviewExpanded;
    }

    public void getData() {
        mProgressBar.setVisibility(View.VISIBLE);
        Disposable disposable = mPersonRepository.getPerson(mIdPerson, BuildConfig.API_KEY.toString())
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) throws Exception {
                        personObservableField.set(person);
                        getMovieByPerson(person.getName());
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

    public void getMovieByPerson(String namePerson) {
        Disposable disposable = mMovieRepository.getAllMovieByPerson(BuildConfig.API_KEY, Constants.LANGUAGE, namePerson)
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(List<ResultMovie> resultMovies) throws Exception {
                        int size = resultMovies.size();
                        mMovieAdapter.setMovies(resultMovies.get(--size).getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onStart() {
        getData();
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
