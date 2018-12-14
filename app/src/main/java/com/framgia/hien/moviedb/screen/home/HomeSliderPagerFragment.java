package com.framgia.hien.moviedb.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dong.moviedb.R;
import com.example.dong.moviedb.databinding.FragmentItemSliderPagerBinding;
import com.framgia.hien.moviedb.screen.BaseFragment;

public class HomeSliderPagerFragment extends BaseFragment {

    private static final String ARGUMENT_PAGE = "ARG_PAGE";
    private static final int IMAGE_SCALE_HEIGHT = 300;
    private static final int IMAGE_SCALE_WIDTH = 600;
    private static final int IMAGE_SLIDER = 0;
    private static final int IMAGE_SLIDER_1 = 1;
    private static final int IMAGE_SLIDER_2 = 2;

    private int mPage;
    private FragmentItemSliderPagerBinding mBinding;

    public HomeSliderPagerFragment() {

    }

    public static HomeSliderPagerFragment getInstance(int page) {
        HomeSliderPagerFragment fragment = new HomeSliderPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_slider_pager, container, false);
        mBinding.setBinding(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) {
            return;
        }

        mPage = getArguments().getInt(ARGUMENT_PAGE);
        ImageView imageView = mBinding.imageSliderItem;
        switch (mPage) {
            case IMAGE_SLIDER:
                setupImage(view, imageView, R.drawable.slider_image);
                break;
            case IMAGE_SLIDER_1:
                setupImage(view, imageView, R.drawable.slider_image1);
                break;
            case IMAGE_SLIDER_2:
                setupImage(view, imageView, R.drawable.slider_image2);
                break;
            default:
                break;
        }
    }

    private void setupImage(View view, ImageView imageView, int imageId) {
        Glide.with(view)
                .load(imageId)
                .apply(new RequestOptions().override(IMAGE_SCALE_WIDTH, IMAGE_SCALE_HEIGHT))
                .into(imageView);
    }
}
