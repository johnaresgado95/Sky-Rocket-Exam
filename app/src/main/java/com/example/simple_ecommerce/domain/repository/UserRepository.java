package com.example.simple_ecommerce.domain.repository;

import com.example.simple_ecommerce.data.dto.request.LoginRequest;
import com.example.simple_ecommerce.data.dto.response.LoginResponse;
import com.example.simple_ecommerce.data.remote.HttpClient;
import com.example.simple_ecommerce.data.remote.HttpService;

import retrofit2.Call;

public class UserRepository {

    private HttpService apiService;

    public UserRepository() {
        apiService = HttpClient.getClient().create(HttpService.class);
    }

    public Call<LoginResponse> loginUser(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        return apiService.loginUser(loginRequest);
    }

}

