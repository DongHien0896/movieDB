package com.framgia.hien.moviedb.data.source.remote;

import com.framgia.hien.moviedb.data.model.Trailer;
import com.framgia.hien.moviedb.data.model.TrailerResponse;
import com.framgia.hien.moviedb.data.source.TrailerDataSource;
import com.framgia.hien.moviedb.data.source.remote.service.MovieApi;
import com.framgia.hien.moviedb.data.source.remote.service.MovieServiceClient;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class TrailerRemoteDataSource implements TrailerDataSource.RemoteDataSource {

    private static TrailerRemoteDataSource sTrailerRemote;
    private MovieApi mApi;

    public TrailerRemoteDataSource(MovieApi api) {
        this.mApi = api;
    }

    public static synchronized TrailerRemoteDataSource getInstance() {
        if (sTrailerRemote == null) {
            sTrailerRemote = new TrailerRemoteDataSource(MovieServiceClient.getInstance());
        }
        return sTrailerRemote;
    }

    @Override
    public Maybe<List<Trailer>> getTrailer(int movieId, String key) {
        return mApi.getTrailer(movieId, key)
                .flatMap(new Function<TrailerResponse, SingleSource<? extends List<Trailer>>>() {
                    @Override
                    public SingleSource<? extends List<Trailer>> apply(TrailerResponse trailerResponse) throws Exception {
                        return Single.just(trailerResponse.getResults());
                    }
                })
                .toMaybe();
    }
}
