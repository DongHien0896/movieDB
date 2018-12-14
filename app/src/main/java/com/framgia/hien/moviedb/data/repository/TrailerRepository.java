package com.framgia.hien.moviedb.data.repository;

import com.framgia.hien.moviedb.data.model.Trailer;
import com.framgia.hien.moviedb.data.source.remote.TrailerRemoteDataSource;

import java.util.List;

import io.reactivex.Maybe;

public class TrailerRepository implements TrailerRemoteDataSource.RemoteDataSource {

    private static TrailerRepository sInstance;
    private TrailerRemoteDataSource mTrailerRemote;

    public TrailerRepository(TrailerRemoteDataSource remoteDataSource) {
        this.mTrailerRemote = remoteDataSource;
    }

    public static synchronized TrailerRepository getInstance(TrailerRemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new TrailerRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public Maybe<List<Trailer>> getTrailer(int movieId, String key) {
        return mTrailerRemote.getTrailer(movieId, key);
    }
}
