package com.framgia.hien.moviedb.screen.detail;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.framgia.hien.moviedb.data.model.Company;

public class ItemCompanyViewModel extends BaseObservable {
    public ObservableField<Company> observableField = new ObservableField<>();
    private CompanyAdapter.ItemClickListener mListener;

    public ItemCompanyViewModel(CompanyAdapter.ItemClickListener listener) {
        this.mListener = listener;
    }

    public void setCompany(Company company) {
        observableField.set(company);
    }

    public void onItemClicked(View view) {
        if (mListener == null || observableField.get() == null) {
            return;
        }
        mListener.onItemClick(observableField.get().getId());
    }
}
