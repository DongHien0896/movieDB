package com.framgia.hien.moviedb.data.repository;

import com.framgia.hien.moviedb.data.model.Genre;
import com.framgia.hien.moviedb.data.source.remote.GenreRemoteDataSource;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

public class GenreRepository implements GenreRemoteDataSource.RemoteDataSource {
    private GenreRemoteDataSource mRemoteDataSource;
    private static GenreRepository sInstance;

    public GenreRepository(@NonNull GenreRemoteDataSource dataSource){
        this.mRemoteDataSource = dataSource;
    }

    public static GenreRepository getsInstance(GenreRemoteDataSource dataSource) {
        if (sInstance == null){
            sInstance = new GenreRepository(dataSource);
        }
        return sInstance;
    }

    @Override
    public Maybe<List<Genre>> getGenres(String key) {
        return mRemoteDataSource.getGenres(key);
    }
}
