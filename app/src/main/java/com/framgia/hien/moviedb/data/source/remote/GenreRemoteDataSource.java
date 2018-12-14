package com.framgia.hien.moviedb.data.source.remote;

import com.framgia.hien.moviedb.data.model.Genre;
import com.framgia.hien.moviedb.data.model.GenresResponse;
import com.framgia.hien.moviedb.data.source.GenreDataSource;
import com.framgia.hien.moviedb.data.source.remote.service.MovieApi;
import com.framgia.hien.moviedb.data.source.remote.service.MovieServiceClient;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {

    private static GenreRemoteDataSource sGenreRemote;
    private MovieApi mApi;

    public GenreRemoteDataSource(MovieApi movieApi){
        this.mApi = movieApi;
    }

    public static synchronized GenreRemoteDataSource getInstance(){
        if (sGenreRemote == null){
            return new GenreRemoteDataSource(MovieServiceClient.getInstance());
        }
        return sGenreRemote;
    }

    @Override
    public Maybe<List<Genre>> getGenres(String key) {
        return mApi.getGenres(key)
                .flatMap(new Function<GenresResponse, SingleSource<? extends List<Genre>>>() {
                    @Override
                    public SingleSource<? extends List<Genre>> apply(GenresResponse genresResponse){
                        return Single.just(genresResponse.getItems());
                    }
                })
                .toMaybe();
    }
}
