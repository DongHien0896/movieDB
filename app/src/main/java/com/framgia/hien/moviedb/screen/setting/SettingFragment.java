package com.framgia.hien.moviedb.screen.setting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.FragmentSettingBinding;
import com.framgia.hien.moviedb.screen.BaseFragment;

public class SettingFragment extends BaseFragment {

    private static SettingFragment sInstance;
    private FragmentSettingBinding mBinding;

    public static SettingFragment getInstance() {
        if (sInstance == null) {
            sInstance = new SettingFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
