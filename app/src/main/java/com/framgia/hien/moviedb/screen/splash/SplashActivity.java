package com.framgia.hien.moviedb.screen.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.framgia.hien.moviedb.screen.BaseActivity;
import com.framgia.hien.moviedb.screen.main.MainActivity;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIMEOUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
