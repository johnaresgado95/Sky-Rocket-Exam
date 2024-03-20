package com.example.simple_ecommerce.domain.repository;

import androidx.annotation.NonNull;

import com.example.simple_ecommerce.data.dto.response.Products;
import com.example.simple_ecommerce.data.remote.HttpService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductsRepository {
    private final HttpService httpService;

    public GetProductsRepository(HttpService httpService) {
        this.httpService = httpService;
    }

    public void getProducts(final ProductsCallback callback) {
        httpService.getProductsLimit().enqueue(new Callback<Products>() {
            @Override
            public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to fetch products");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public interface ProductsCallback {
        void onSuccess(Products products);
        void onError(String message);
    }
}
