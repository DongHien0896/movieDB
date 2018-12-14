package com.framgia.hien.moviedb.data.source.remote.service;

import android.app.Application;
import android.support.annotation.NonNull;

import com.framgia.hien.moviedb.util.Constants;

public class MovieServiceClient extends ServiceClient {
    private static MovieApi mMovieApi;
    private static final String MESSAGE_INSTANCE_ERRO = " is not initialized, call initialize(..) method first.";

    public static void initialize(@NonNull Application application) {
        mMovieApi = createService(application, Constants.END_POINT_URL, MovieApi.class);
    }

    public static MovieApi getInstance() {
        if (mMovieApi == null) {
            String error = (new StringBuilder()).append(MovieServiceClient.class.getSimpleName())
                    .append(MESSAGE_INSTANCE_ERRO).toString();
            throw new IllegalStateException(error);
        }
        return mMovieApi;
    }
}
