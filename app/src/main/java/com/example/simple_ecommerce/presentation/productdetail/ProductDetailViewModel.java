package com.example.simple_ecommerce.presentation.productdetail;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simple_ecommerce.data.dto.response.Product;
import com.example.simple_ecommerce.data.remote.HttpClient;
import com.example.simple_ecommerce.domain.repository.GetProductsRepository;
import com.example.simple_ecommerce.domain.repository.GetSingleProductRepository;

public class ProductDetailViewModel extends ViewModel {
    private final MutableLiveData<Product> photodetail = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final GetSingleProductRepository getSingleProductRepository;

    public ProductDetailViewModel() {
        getSingleProductRepository = new GetSingleProductRepository(new HttpClient().getHttpClient(), GetSingleProductRepository.getPhotoId());
        getSingleProductRepository.getPhotoDetail(new GetSingleProductRepository.ProductDetailCallback() {
            @Override
            public void onSuccess(Product productDetail) {
                Log.d(TAG, productDetail.toString());
                photodetail.setValue(productDetail);
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, message);
                errorMessage.setValue(message);
            }
        });
    }

    public MutableLiveData<Product> getProductDetail() {
        return photodetail;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
