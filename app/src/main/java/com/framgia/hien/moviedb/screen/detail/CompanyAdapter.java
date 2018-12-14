package com.framgia.hien.moviedb.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemCompanyRecyclerBinding;
import com.framgia.hien.moviedb.data.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ItemViewHolder>{

    private List<Company> mCompanies;
    private ItemClickListener mItemClick;

    public CompanyAdapter(){
        mCompanies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompanyRecyclerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_company_recycler, parent, false);
        return new ItemViewHolder(binding, mItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return mCompanies == null ? 0 : mCompanies.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClick = itemClickListener;
    }

    public void setCompanies(List<Company> companies){
        mCompanies.clear();
        mCompanies.addAll(companies);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemCompanyRecyclerBinding mBinding;
        private ItemCompanyViewModel mItemCompany;

        public ItemViewHolder(ItemCompanyRecyclerBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemCompany = new ItemCompanyViewModel(listener);
            mBinding.setViewModel(mItemCompany);
        }

        private void bind(Company company) {
            mItemCompany.setCompany(company);
            mBinding.executePendingBindings();
        }
    }

    interface ItemClickListener{
        void onItemClick(int CompanyId);
    }
}
