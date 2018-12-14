package com.framgia.hien.moviedb.screen.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ActivityMainBinding;
import com.framgia.hien.moviedb.screen.BaseActivity;
import com.framgia.hien.moviedb.util.network.NetworkReceiver;

public class MainActivity extends BaseActivity {

    private MainViewModel mViewModel;
    private SearchView mSearchView;
    private MenuItem mSearchMenu;
    private BottomNavigationView mBottomNavigationView;
    private NetworkReceiver mNetworkReceiver;
    private SwipeRefreshLayout mRefreshLayout;
    private ActivityMainBinding mBinding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mViewModel.onNavigationItemSelected(item);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new MainViewModel(this);
        mBinding.setMainViewModel(mViewModel);
        mBottomNavigationView = findViewById(R.id.navigation_bottom);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewModel.setBottomNavigation(mBottomNavigationView);
        Toolbar toolbar = mBinding.toolbar;
        mRefreshLayout = mBinding.swiperefresh;
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadView();
            }
        });
        setSupportActionBar(toolbar);
        checkInternet();
    }

    private void reloadView() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(true);
        mSearchMenu = menu.findItem(R.id.action_search);
        mViewModel.setMenuSearch(mSearchView, mSearchMenu);
        return true;
    }

    protected void showDialogNoInternet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getString(R.string.app_name));
        builder.setMessage(this.getString(R.string.message_dialog_no_internet));
        AlertDialog dialog = builder.create();
        if (dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    private void checkInternet() {
        initNetworkBroadcastReceiver(new NetworkReceiver.NetworkStateListener() {
            @Override
            public void onNetworkConnected() {

            }

            @Override
            public void onNetworkDisconnected() {
                showDialogNoInternet();
            }
        });
    }

    public void initNetworkBroadcastReceiver(NetworkReceiver.NetworkStateListener listener) {
        mNetworkReceiver = new NetworkReceiver(listener);
    }

    @Override
    protected void onStart() {
        if (mNetworkReceiver == null) {
            return;
        }
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager
                .CONNECTIVITY_ACTION));
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mNetworkReceiver == null) {
            return;
        }
        unregisterReceiver(mNetworkReceiver);
        super.onStop();
    }
}
