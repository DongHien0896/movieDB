package com.framgia.hien.moviedb.util.rx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScheduleProvider implements BaseScheduleProvider {

    @Nullable
    private static ScheduleProvider sInstance;

    private ScheduleProvider() {

    }

    public static ScheduleProvider getInstance() {
        if (sInstance == null) {
            sInstance = new ScheduleProvider();
        }
        return sInstance;
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
