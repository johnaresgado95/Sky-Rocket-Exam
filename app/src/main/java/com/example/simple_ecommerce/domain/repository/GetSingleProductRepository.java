package com.example.simple_ecommerce.domain.repository;

import com.example.simple_ecommerce.data.dto.response.Product;
import com.example.simple_ecommerce.data.remote.HttpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSingleProductRepository {
    private HttpService httpService;

    public static int photoId;

    public GetSingleProductRepository(HttpService httpService, int photoId) {
        this.httpService = httpService;
        GetSingleProductRepository.photoId = photoId;
    }

    public static void setPhotoId(int photoId) {
        GetSingleProductRepository.photoId = photoId;
    }

    public static int getPhotoId() {
        return photoId;
    }

    public void getPhotoDetail(final GetSingleProductRepository.ProductDetailCallback callback) {
        httpService.getSingleProduct(GetSingleProductRepository.photoId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to fetch product");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public interface ProductDetailCallback {
        void onSuccess(Product photos);
        void onError(String message);
    }
}
