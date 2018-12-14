package com.framgia.hien.moviedb.screen.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framgia.hien.moviedb.util.Constants;

public class HomeSliderFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public HomeSliderFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return HomeSliderPagerFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return Constants.HOME_SLIDER_TOTAL_PAGE;
    }
}
