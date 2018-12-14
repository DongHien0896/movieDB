package com.framgia.hien.moviedb.data.repository;

import com.framgia.hien.moviedb.data.model.Cast;
import com.framgia.hien.moviedb.data.source.remote.CastRemoteDataSource;

import java.util.List;

import io.reactivex.Maybe;

public class CastRepository implements CastRemoteDataSource.RemoteDataSource {

    private CastRemoteDataSource mRemoteDataSource;
    private static CastRepository sInstance;

    public CastRepository(CastRemoteDataSource remoteDataSource) {
        this.mRemoteDataSource = remoteDataSource;
    }

    public static synchronized CastRepository getsInstance(CastRemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new CastRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public Maybe<List<Cast>> getCastOfMovie(int movieId, String key) {
        return mRemoteDataSource.getCastOfMovie(movieId, key);
    }
}
