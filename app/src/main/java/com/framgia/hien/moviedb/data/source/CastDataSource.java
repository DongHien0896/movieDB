package com.framgia.hien.moviedb.data.source;

import com.framgia.hien.moviedb.data.model.Cast;

import java.util.List;

import io.reactivex.Maybe;

public interface CastDataSource {
    Maybe<List<Cast>> getCastOfMovie(int movieId, String key);

    interface RemoteDataSource extends CastDataSource {
    }
}
