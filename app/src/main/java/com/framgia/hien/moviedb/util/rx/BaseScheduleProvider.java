package com.framgia.hien.moviedb.util.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface BaseScheduleProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
