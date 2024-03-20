package com.example.simple_ecommerce.presentation;

import static com.example.simple_ecommerce.BuildConfig.BUILD_TYPE;
import static com.example.simple_ecommerce.BuildConfig.VERSION_CODE;
import static com.example.simple_ecommerce.BuildConfig.VERSION_NAME;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.data.util.SharedPreferencesHelper;
import com.example.simple_ecommerce.presentation.auth.signin.SignInActivity;
import com.example.simple_ecommerce.presentation.home.HomeActivity;
import com.example.simple_ecommerce.presentation.onboarding.view.OnBoardingActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView appVersion = findViewById(R.id.app_version);
        appVersion.setText(BUILD_TYPE + ": " + VERSION_CODE + "." + VERSION_NAME);
        boolean isAppOpenedFirstTime = SharedPreferencesHelper.getOnboardingStatus(this);
        String token = SharedPreferencesHelper.getLoginToken(this, "");
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (!token.isEmpty())
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    else
                        startActivity(new Intent(getApplicationContext(), isAppOpenedFirstTime ? SignInActivity.class : OnBoardingActivity.class));
                    finish();
                }
            }
        };
        timer.start();
    }
}