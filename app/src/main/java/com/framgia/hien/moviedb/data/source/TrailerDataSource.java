package com.framgia.hien.moviedb.data.source;

import com.framgia.hien.moviedb.data.model.Trailer;

import java.util.List;

import io.reactivex.Maybe;

public interface TrailerDataSource {
    Maybe<List<Trailer>> getTrailer(int movieId, String key);

    interface RemoteDataSource extends TrailerDataSource {
    }
}
