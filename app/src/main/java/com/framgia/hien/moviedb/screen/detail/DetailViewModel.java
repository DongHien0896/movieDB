package com.framgia.hien.moviedb.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dong.moviedb.BuildConfig;
import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.data.model.Cast;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.repository.CastRepository;
import com.framgia.hien.moviedb.data.repository.MovieRepository;
import com.framgia.hien.moviedb.screen.BaseViewModel;
import com.framgia.hien.moviedb.screen.company.CompanyActivity;
import com.framgia.hien.moviedb.screen.favorite.FavoriteAdapter;
import com.framgia.hien.moviedb.screen.person.PersonActivity;
import com.framgia.hien.moviedb.screen.play.PlayActivity;
import com.framgia.hien.moviedb.util.Constants;
import com.framgia.hien.moviedb.util.rx.BaseScheduleProvider;
import com.framgia.hien.moviedb.util.rx.ScheduleProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DetailViewModel extends BaseViewModel implements CompanyAdapter.ItemClickListener,
        CastAdapter.ItemCastClickListener {

    private static final int DEFAULT_VALUE = -1;
    private AppCompatActivity mActivity;
    private MovieRepository mMovieRepository;
    private CastRepository mCastRepository;
    public ObservableField<Movie> movieObservableField = new ObservableField<>();
    private BaseScheduleProvider mBaseScheduleProvider;
    private CompositeDisposable mCompositeDisposable;
    private int mMovieId;
    private ProgressBar mProgressBar;
    private BackPressListener mBackPress;
    private boolean mIsTextOverviewExpanded;
    private TextView mTextOverview;
    private CompanyAdapter mCompanyAdapter;
    private CastAdapter mCastAdapter;
    private ImageView mImageBackDrop;
    private ImageView mImageTrailer;
    private ImageButton mFavoriteIcon;
    private FavoriteAdapter.FavoriteListener mFavoriteListener;

    public static final int OVEVERVIEW_MINLINE = 3;
    public static final int OVEVERVIEW_MAXLINE = 30;

    public DetailViewModel(AppCompatActivity appCompatActivity, int movieId,
                           BackPressListener backPressListener,
                           FavoriteAdapter.FavoriteListener listener) {
        this.mActivity = appCompatActivity;
        this.mMovieId = movieId;
        this.mBackPress = backPressListener;
        mFavoriteListener = listener;
        setComponent();
    }

    private void setComponent() {
        this.mBaseScheduleProvider = ScheduleProvider.getInstance();
        mCompositeDisposable = new CompositeDisposable();
        mCompanyAdapter = new CompanyAdapter();
        mCastAdapter = new CastAdapter();
        mCastAdapter.setItemClickListener(this);
        mCompanyAdapter.setItemClickListener(this);
    }

    public void setImagView(ImageView imageBackDrop, ImageView imageTrailer) {
        this.mImageBackDrop = imageBackDrop;
        this.mImageTrailer = imageTrailer;
    }

    public void setImageButton(ImageButton imageButton) {
        this.mFavoriteIcon = imageButton;
    }

    public boolean checkFavorite(Movie movie) {
        return mMovieRepository.isExistMovie(movie);
    }

    public void addMovie(Movie movie) {
        mMovieRepository.addMovie(movie);
    }

    public void removeMovie(Movie movie) {
        mMovieRepository.removeMovie(movie);
    }

    public void setRepository(MovieRepository movieRepository, CastRepository castRepository) {
        this.mMovieRepository = movieRepository;
        this.mCastRepository = castRepository;
    }

    public CompanyAdapter getCompanyAdapter() {
        return mCompanyAdapter;
    }

    public CastAdapter getCastAdapter() {
        return mCastAdapter;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void initFavorite() {
        if (checkFavorite(movieObservableField.get())) {
            updateResId(true);
        } else {
            updateResId(false);
        }
    }

    public void updateResId(boolean isFavorite) {
        if (isFavorite) {
            mFavoriteIcon.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        } else {
            mFavoriteIcon.setBackgroundResource(R.drawable.ic_favorite_border_red_24dp);
        }
    }

    public void setTextView(TextView textView) {
        this.mTextOverview = textView;
    }

    public void getMovieDetail() {
        mProgressBar.setVisibility(View.VISIBLE);
        Disposable disposable = mMovieRepository.getDetailMovie(mMovieId, BuildConfig.API_KEY.toString())
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) throws Exception {
                        movieObservableField.set(movie);
                        mCompanyAdapter.setCompanies(movie.getCompanies());
                        loadUrl(mImageBackDrop, movie.getBackdropPath());
                        loadUrl(mImageTrailer, movie.getPosterPath());
                        initFavorite();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void getCast() {
        Disposable disposable = mCastRepository.getCastOfMovie(mMovieId, BuildConfig.API_KEY.toString())
                .subscribeOn(mBaseScheduleProvider.io())
                .observeOn(mBaseScheduleProvider.ui())
                .subscribe(new Consumer<List<Cast>>() {
                    @Override
                    public void accept(List<Cast> casts) throws Exception {
                        mCastAdapter.setCast(casts);
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

    public static void loadUrl(ImageView image, String url) {
        String path = Constants.END_POINT_IMAGE_URL.concat(url);
        Glide.with(image.getContext())
                .load(path)
                .apply(new RequestOptions().placeholder(R.drawable.movie_detail_poster_sample))
                .into(image);
    }

    @Override
    protected void onStart() {
        getMovieDetail();
        getCast();
    }

    @Override
    protected void onStop() {

    }

    public void onBackClicked(View view) {
        mBackPress.backPress();
    }

    public void onTextClicked(View view) {
        if (mIsTextOverviewExpanded) {
            mTextOverview.setMaxLines(OVEVERVIEW_MINLINE);
        } else {
            mTextOverview.setMaxLines(OVEVERVIEW_MAXLINE);
        }
        mIsTextOverviewExpanded = !mIsTextOverviewExpanded;
    }

    public void onTrailerClicked(View view) {
        Movie movie = movieObservableField.get();
        if (movie == null) {
            Toast.makeText(mActivity.getApplicationContext(), Constants.MESSAGE_ERROR_PLAY_TRAILER, Toast.LENGTH_LONG).show();
            return;
        }
        mActivity.startActivity(getMovieIntent(mActivity.getApplicationContext(), movie));
    }

    public static Intent getMovieIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(Constants.MOVIE, movie);
        return intent;
    }

    public void onFavoriteClick(View view) {
        mFavoriteListener.onFavoriteClick(movieObservableField.get());
    }

    @Override
    public void onItemClick(int companyId) {
        if (companyId == DEFAULT_VALUE) {
            Toast.makeText(mActivity.getApplicationContext(),
                    Constants.MESSAGE_ERROR, Toast.LENGTH_LONG).show();
        }
        mActivity.startActivity(getCompanyIntent(mActivity.getApplicationContext(), companyId));
    }

    public static Intent getCompanyIntent(Context context, int companyId) {
        Intent intent = new Intent(context, CompanyActivity.class);
        intent.putExtra(Constants.COMPANY, companyId);
        return intent;
    }

    @Override
    public void onItemCastClick(int castId) {
        if (castId == DEFAULT_VALUE) {
            Toast.makeText(mActivity.getApplicationContext(),
                    Constants.MESSAGE_ERROR, Toast.LENGTH_LONG).show();
            return;
        }
        mActivity.startActivity(getCastIntent(mActivity.getApplicationContext(), castId));
    }

    public static Intent getCastIntent(Context context, int castId) {
        Intent intent = new Intent(context, PersonActivity.class);
        intent.putExtra(Constants.PERSON, castId);
        return intent;
    }

    interface BackPressListener {
        void backPress();
    }
}
