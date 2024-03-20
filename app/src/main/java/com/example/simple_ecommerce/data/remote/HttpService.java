package com.example.simple_ecommerce.data.remote;

import com.example.simple_ecommerce.data.dto.request.LoginRequest;
import com.example.simple_ecommerce.data.dto.response.LoginResponse;
import com.example.simple_ecommerce.data.dto.response.Product;
import com.example.simple_ecommerce.data.dto.response.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpService {
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest request);

    @GET("products")
    Call<Products> getProducts();

    @GET("products?limit=10")
    Call<Products> getProductsLimit();

    @GET("products/{id}")
    Call<Product> getSingleProduct(@Path("id") int id);

    @GET("products/search")
    Call<Products> searchProducts(@Query("q") String query);
}
