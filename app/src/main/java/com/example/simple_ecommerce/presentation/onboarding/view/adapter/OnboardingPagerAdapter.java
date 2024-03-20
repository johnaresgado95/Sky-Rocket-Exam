package com.example.simple_ecommerce.presentation.onboarding.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class OnboardingPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mOnboardingScreens;

    public OnboardingPagerAdapter(Context context, int[] onboardingScreens) {
        mContext = context;
        mOnboardingScreens = onboardingScreens;
    }

    @Override
    public int getCount() {
        return mOnboardingScreens.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mOnboardingScreens[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

