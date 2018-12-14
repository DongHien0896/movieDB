package com.framgia.hien.moviedb.data.source.remote;

import com.framgia.hien.moviedb.data.model.Company;
import com.framgia.hien.moviedb.data.source.CompanyDataSource;
import com.framgia.hien.moviedb.data.source.remote.service.MovieApi;
import com.framgia.hien.moviedb.data.source.remote.service.MovieServiceClient;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class CompanyRemoteDataSource implements CompanyDataSource.RemoteDataSource {

    private static CompanyRemoteDataSource sInstance;
    private MovieApi mApi;

    public CompanyRemoteDataSource(MovieApi movieApi) {
        this.mApi = movieApi;
    }

    public static synchronized CompanyRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new CompanyRemoteDataSource(MovieServiceClient.getInstance());
        }
        return sInstance;
    }

    @Override
    public Maybe<Company> getCompanyDetail(int companyId, String key) {
        return mApi.getCompany(companyId, key)
                .flatMap(new Function<Company, SingleSource<? extends Company>>() {
                    @Override
                    public SingleSource<? extends Company> apply(Company company) throws Exception {
                        return Single.just(company);
                    }
                })
                .toMaybe();
    }
}
