package com.example.simple_ecommerce.presentation.auth.signin;

import static com.example.simple_ecommerce.BuildConfig.BUILD_TYPE;
import static com.example.simple_ecommerce.BuildConfig.VERSION_CODE;
import static com.example.simple_ecommerce.BuildConfig.VERSION_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.simple_ecommerce.BuildConfig;
import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.data.util.SharedPreferencesHelper;
import com.example.simple_ecommerce.presentation.home.HomeActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private SignInViewModel signInViewModel;
    private Chip signButton;
    private TextInputEditText username, password;
    private TextView appVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signButton = findViewById(R.id.btn_signIn);
        username = findViewById(R.id.ti_username);
        password = findViewById(R.id.ti_password);
        appVersion = findViewById(R.id.app_version);

        if (BuildConfig.DEBUG) {
            username.setText("kminchelle");
            password.setText("0lelplR");
            appVersion.setText(BUILD_TYPE + ": " + VERSION_CODE + "." + VERSION_NAME);
        }

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        signButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String uname = Objects.requireNonNull(username.getText()).toString().trim();
        String pword = Objects.requireNonNull(password.getText()).toString().trim();
        signInViewModel.loginUser(uname, pword);
        signInViewModel.getLoginResult().observe(this, loginResult -> {
            SharedPreferencesHelper.saveLoginToken(this, loginResult);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
}