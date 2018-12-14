package com.framgia.hien.moviedb.data.repository;

import com.framgia.hien.moviedb.data.model.Company;
import com.framgia.hien.moviedb.data.source.remote.CompanyRemoteDataSource;

import io.reactivex.Maybe;

public class CompanyRepository implements CompanyRemoteDataSource.RemoteDataSource {

    private static CompanyRepository sInstance;
    private CompanyRemoteDataSource mRemoteDataSource;

    public CompanyRepository(CompanyRemoteDataSource dataSource) {
        this.mRemoteDataSource = dataSource;
    }

    public static synchronized CompanyRepository getInstance(CompanyRemoteDataSource dataSource) {
        if (sInstance == null) {
            sInstance = new CompanyRepository(dataSource);
        }
        return sInstance;
    }

    @Override
    public Maybe<Company> getCompanyDetail(int companyId, String key) {
        return mRemoteDataSource.getCompanyDetail(companyId, key);
    }
}
