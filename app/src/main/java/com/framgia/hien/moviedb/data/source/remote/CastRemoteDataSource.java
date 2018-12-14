package com.framgia.hien.moviedb.data.source.remote;

import com.framgia.hien.moviedb.data.model.Cast;
import com.framgia.hien.moviedb.data.model.CastResponse;
import com.framgia.hien.moviedb.data.source.CastDataSource;
import com.framgia.hien.moviedb.data.source.remote.service.MovieApi;
import com.framgia.hien.moviedb.data.source.remote.service.MovieServiceClient;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class CastRemoteDataSource implements CastDataSource.RemoteDataSource {

    private static CastRemoteDataSource sCastRemote;
    private MovieApi mApi;

    public CastRemoteDataSource(MovieApi movieApi) {
        this.mApi = movieApi;
    }

    public static synchronized CastRemoteDataSource getInstance() {
        if (sCastRemote == null) {
            sCastRemote = new CastRemoteDataSource(MovieServiceClient.getInstance());
        }
        return sCastRemote;
    }

    @Override
    public Maybe<List<Cast>> getCastOfMovie(int movieId, String key) {
        return mApi.getCastOfMovie(movieId, key)
                .flatMap(new Function<CastResponse, SingleSource<? extends List<Cast>>>() {
                    @Override
                    public SingleSource<? extends List<Cast>> apply(CastResponse castResponse) throws Exception {
                        return Single.just(castResponse.getCast());
                    }
                })
                .toMaybe();
    }
}
