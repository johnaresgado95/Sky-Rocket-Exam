package com.example.simple_ecommerce.presentation.auth.signin;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simple_ecommerce.data.dto.response.LoginResponse;
import com.example.simple_ecommerce.domain.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {

    private MutableLiveData<String> loginResult = new MutableLiveData<>();
    private UserRepository userRepository;

    public SignInViewModel() {
        userRepository = new UserRepository();
    }

    public MutableLiveData<String> getLoginResult() {
        return loginResult;
    }

    public void loginUser(String username, String password) {
        userRepository.loginUser(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResult.setValue(response.body().getToken());
                } else {
                    loginResult.setValue("Login failed");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResult.setValue(t.getMessage());
            }
        });
    }
}
