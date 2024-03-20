package com.example.simple_ecommerce.presentation.onboarding.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.data.util.Constants;
import com.example.simple_ecommerce.data.util.SharedPreferencesHelper;
import com.example.simple_ecommerce.presentation.auth.signin.SignInActivity;
import com.example.simple_ecommerce.presentation.onboarding.view.adapter.OnboardingPagerAdapter;
import com.google.android.material.chip.Chip;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mIndicatorLayout;
    private Chip mBtnDone;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        mViewPager = findViewById(R.id.viewPager);
        mIndicatorLayout = findViewById(R.id.indicatorLayout);
        mBtnDone = findViewById(R.id.btnDone);
        addDotsIndicator(0);

        int[] onboardingScreens = {R.layout.item_onboarding_1, R.layout.item_onboarding_2, R.layout.item_onboarding_3};
        OnboardingPagerAdapter adapter = new OnboardingPagerAdapter(this, onboardingScreens);
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                mBtnDone.setText(position == onboardingScreens.length - 1 ? R.string.get_started : R.string.next);
                addDotsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mBtnDone.setOnClickListener(v -> {
            if (mCurrentPage < onboardingScreens.length - 1) {
                mViewPager.setCurrentItem(mCurrentPage + 1);
            } else {
                SharedPreferencesHelper.saveOnboardingStatus(this, true);
                startActivity(new Intent(this, SignInActivity.class));
                finish();
            }
        });
    }

    private void addDotsIndicator(int position) {
        mIndicatorLayout.removeAllViews();
        for (int i = 0; i < 3; i++) { // Assuming there are 3 screens
            ImageView imageView = new ImageView(this);
            if (i == position) {
                imageView.setImageResource(R.drawable.indicator_selected); // Selected dot
            } else {
                imageView.setImageResource(R.drawable.indicator_unselected); // Unselected dot
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            mIndicatorLayout.addView(imageView, params);
        }
    }
}
