package com.framgia.hien.moviedb.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.ItemCastRecyclerBinding;
import com.framgia.hien.moviedb.data.model.Cast;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ItemViewHolder> {

    private List<Cast> mCasts;
    private ItemCastClickListener mItemClick;

    public CastAdapter() {
        mCasts = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastRecyclerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_cast_recycler, parent, false);
        return new ItemViewHolder(binding, mItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mCasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mCasts == null ? 0 : mCasts.size();
    }

    public void setItemClickListener(ItemCastClickListener itemClickListener) {
        mItemClick = itemClickListener;
    }

    public void setCast(List<Cast> cast) {
        mCasts.clear();
        mCasts.addAll(cast);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemCastRecyclerBinding mBinding;
        private ItemCastViewModel mViewModel;

        public ItemViewHolder(ItemCastRecyclerBinding binding, ItemCastClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemCastViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Cast cast) {
            mViewModel.setObservable(cast);
            mBinding.executePendingBindings();
        }
    }

    interface ItemCastClickListener {
        void onItemCastClick(int castId);
    }
}
