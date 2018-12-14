package com.framgia.hien.moviedb.data.source;

import com.framgia.hien.moviedb.data.model.Person;

import io.reactivex.Maybe;

public interface PersonDataSource {
    Maybe<Person> getPerson(int personId, String key);

    interface RemoteDataSource extends PersonDataSource {
    }
}
