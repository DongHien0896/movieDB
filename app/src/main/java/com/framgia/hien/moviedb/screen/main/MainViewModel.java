package com.framgia.hien.moviedb.screen.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;

import com.example.dong.moviedb.R;
import com.framgia.hien.moviedb.data.model.Genre;
import com.framgia.hien.moviedb.screen.BaseViewModel;
import com.framgia.hien.moviedb.screen.favorite.FavoriteFragment;
import com.framgia.hien.moviedb.screen.home.HomeFragment;
import com.framgia.hien.moviedb.screen.home.HomeFragmentViewModel;
import com.framgia.hien.moviedb.screen.search.SearchFragment;
import com.framgia.hien.moviedb.screen.setting.SettingFragment;

public class MainViewModel extends BaseViewModel implements BottomNavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
        HomeFragmentViewModel.OnClickSearchMoviesByGenre {

    private Fragment mFragment;
    private HomeFragment mHomeFragment;
    private FavoriteFragment mFavoriteFragment;
    private FragmentManager mFragmentManager;
    private SettingFragment mSettingFragment;

    private SearchView mSearchView;
    private MenuItem mSearchMenu;
    private SearchFragment mSearchFragment;
    private BottomNavigationView mBottomNavigationView;

    public MainViewModel(AppCompatActivity appCompatActivity) {
        mFragmentManager = appCompatActivity.getSupportFragmentManager();
        createComponent();
    }

    @Override
    protected void onStart() {
    }

    @Override
    protected void onStop() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mSearchMenu.setVisible(true);
                hideShowFragment(mFragment, mHomeFragment);
                setHomeFragment();
                mFragment = mHomeFragment;
                return true;
            case R.id.navigation_favorite:
                hideShowFragment(mFragment, mFavoriteFragment);
                mFragment = mFavoriteFragment;
                mSearchMenu.setVisible(false);
                return true;
            case R.id.navigation_setting:
                hideShowFragment(mFragment, mSettingFragment);
                mFragment = mSettingFragment;
                mSearchMenu.setVisible(false);
                return true;
        }
        return false;
    }

    private void setHomeFragment() {
        mHomeFragment.setClickGenreItem(this);
    }

    private void createComponent() {
        mHomeFragment = HomeFragment.getsInstance();
        mFavoriteFragment = FavoriteFragment.getsInstance();
        mSettingFragment = SettingFragment.getInstance();
        addHideFragment(mSettingFragment);
        addHideFragment(mFavoriteFragment);
        mFragmentManager.beginTransaction().add(R.id.frame_container, mHomeFragment).commit();
        mFragment = mHomeFragment;
    }

    private void addHideFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().add(R.id.frame_container, fragment).hide(fragment).commit();
    }

    private void hideShowFragment(Fragment hide, Fragment show) {
        mFragmentManager.beginTransaction().hide(hide).show(show).commit();
    }


    public void setMenuSearch(SearchView search, MenuItem menuItem) {
        this.mSearchView = search;
        this.mSearchMenu = menuItem;
        mSearchView.setFocusableInTouchMode(true);
        mSearchView.setOnQueryTextListener(this);
        mSearchMenu.setOnActionExpandListener(this);
        mHomeFragment.setClickGenreItem(this);
    }

    public void setBottomNavigation(BottomNavigationView bottomNavigation) {
        this.mBottomNavigationView = bottomNavigation;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchView.setQuery(query, false);
        mSearchFragment.searchForResult(query);
        mSearchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        mBottomNavigationView.setVisibility(View.GONE);
        mSearchFragment = SearchFragment.getInstance();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_container, mSearchFragment)
                .hide(mFragment)
                .addToBackStack(null)
                .commit();
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        mBottomNavigationView.setVisibility(View.VISIBLE);
        mFragmentManager.popBackStack();
        return true;
    }

    @Override
    public void searchMoviesByGenre(Genre genre) {
        mSearchMenu.expandActionView();
        mSearchFragment.setGenre(genre);
        mSearchView.setQuery(genre.getName(), false);
        mSearchView.clearFocus();
    }
}
