package com.framgia.hien.moviedb.data.repository;

import com.framgia.hien.moviedb.data.model.Person;
import com.framgia.hien.moviedb.data.source.remote.PersonRemoteDataSource;

import io.reactivex.Maybe;

public class PersonRepository implements PersonRemoteDataSource.RemoteDataSource {

    private static PersonRepository sInstance;
    private PersonRemoteDataSource mTrailerRemote;

    public PersonRepository(PersonRemoteDataSource personRemoteDataSource) {
        this.mTrailerRemote = personRemoteDataSource;
    }

    public static synchronized PersonRepository getInstance(PersonRemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new PersonRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public Maybe<Person> getPerson(int personId, String key) {
        return mTrailerRemote.getPerson(personId, key);
    }
}
