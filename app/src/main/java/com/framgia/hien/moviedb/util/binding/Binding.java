package com.framgia.hien.moviedb.util.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dong.moviedb.BuildConfig;
import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.screen.home.HomeSliderFragmentPagerAdapter;
import com.framgia.hien.moviedb.util.Constants;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Binding {
    @BindingAdapter({"onNavigationItemSelected"})
    public static void setBottomNavigationClick(BottomNavigationView bottomNavigationView,
                                                BottomNavigationView.OnNavigationItemSelectedListener listener) {
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter({"setPagerAdapter"})
    public static void setViewPager(ViewPager viewPager, HomeSliderFragmentPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"recyclerAdapter"})
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
                                                 RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadUrl(ImageView image, String url) {
        if (url == null) {
            url = Constants.EXAMPLE_URL;
        }
        String path = Constants.END_POINT_IMAGE_URL.concat(url);
        Glide.with(image.getContext())
                .load(path)
                .apply(new RequestOptions().placeholder(R.drawable.movie_detail_poster_sample))
                .into(image);
    }

    @BindingAdapter({"onYouTubeInitializedListener"})
    public static void setOnYouTubeInitializedListener(YouTubePlayerView youTubeView,
                                                       YouTubePlayer.OnInitializedListener listener) {
        if (listener != null) {
            youTubeView.initialize(BuildConfig.API_KEY_YOUTUBE, listener);
        }
    }

    @BindingAdapter({"ratingBar"})
    public static void setRatingBar(RatingBar ratingBar, float rating) {
        ratingBar.setRating(rating);
    }

    @BindingAdapter({"onRefreshListener"})
    public static void setOnRefreshLayoutListener(SwipeRefreshLayout layout,
                                                  SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"refreshLayout"})
    public static void setRefreshLayout(SwipeRefreshLayout layout, boolean isRefresh) {
        layout.setRefreshing(isRefresh);
    }

    @BindingAdapter({"colorRefreshLayout"})
    public static void setColorRefreshLayout(SwipeRefreshLayout layout, int color) {
        layout.setColorSchemeResources(color);
    }
}
