package com.example.simple_ecommerce.presentation.searchproduct;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simple_ecommerce.data.dto.response.Product;
import com.example.simple_ecommerce.data.dto.response.Products;
import com.example.simple_ecommerce.data.remote.HttpClient;
import com.example.simple_ecommerce.domain.repository.GetSearchProductRepository;

public class SearchViewModel extends ViewModel {
    private  MutableLiveData<Products> products = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final GetSearchProductRepository getSearchProductRepository;

    public SearchViewModel() {
        getSearchProductRepository = new GetSearchProductRepository(new HttpClient().getHttpClient(), GetSearchProductRepository.getQuery());
        getSearchProductRepository.getSearchProducts(new GetSearchProductRepository.ProductsCallback() {

            @Override
            public void onSuccess(Products productsList) {
                products.setValue(productsList);
            }

            @Override
            public void onError(String message) {
                errorMessage.setValue(message);
            }
        });
    }

    public MutableLiveData<Products> getProductDetail() {
        return products;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
