package com.example.simple_ecommerce.presentation.home.fragments.home;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simple_ecommerce.data.dto.response.Products;
import com.example.simple_ecommerce.data.remote.HttpClient;
import com.example.simple_ecommerce.domain.repository.GetProductsRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<Products> products = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final GetProductsRepository productsRepository;

    public HomeViewModel() {
        productsRepository = new GetProductsRepository(new HttpClient().getHttpClient());
        fetchPhotos();
    }

    private void fetchPhotos() {
        productsRepository.getProducts(new GetProductsRepository.ProductsCallback() {
            @Override
            public void onSuccess(Products productsList) {
                Log.d(TAG, productsList.toString());
                products.setValue(productsList);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, message);
                errorMessage.setValue(message);
            }
        });
    }

    public MutableLiveData<Products> getProducts() {
        return products;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
