package com.framgia.hien.moviedb.data.source.remote;

import com.framgia.hien.moviedb.data.model.Person;
import com.framgia.hien.moviedb.data.source.PersonDataSource;
import com.framgia.hien.moviedb.data.source.remote.service.MovieApi;
import com.framgia.hien.moviedb.data.source.remote.service.MovieServiceClient;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class PersonRemoteDataSource implements PersonDataSource.RemoteDataSource {

    private static PersonRemoteDataSource sPersonRemote;
    private MovieApi mApi;

    public PersonRemoteDataSource(MovieApi api) {
        this.mApi = api;
    }

    public static synchronized PersonRemoteDataSource getInstance() {
        if (sPersonRemote == null) {
            sPersonRemote = new PersonRemoteDataSource(MovieServiceClient.getInstance());
        }
        return sPersonRemote;
    }

    @Override
    public Maybe<Person> getPerson(int personId, String key) {
        return mApi.getPerson(personId, key)
                .flatMap(new Function<Person, SingleSource<? extends Person>>() {
                    @Override
                    public SingleSource<? extends Person> apply(Person person) throws Exception {
                        return Single.just(person);
                    }
                })
                .toMaybe();
    }
}
