package com.framgia.hien.moviedb;

import android.app.Application;

import com.framgia.hien.moviedb.data.source.remote.service.MovieServiceClient;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MovieServiceClient.initialize(this);
    }
}
