package com.framgia.hien.moviedb.data.source;

import com.framgia.hien.moviedb.data.model.Company;

import io.reactivex.Maybe;

public interface CompanyDataSource {
    Maybe<Company> getCompanyDetail(int companyId, String key);

    interface RemoteDataSource extends CompanyDataSource {

    }
}
