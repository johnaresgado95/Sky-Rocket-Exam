package com.example.simple_ecommerce.presentation.productdetail;

import static com.example.simple_ecommerce.data.util.DisplayUtil.computeSale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.domain.repository.GetSingleProductRepository;
import com.example.simple_ecommerce.presentation.productdetail.adapter.ImageAdapter;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private List<String> imageUrls;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private TextView product_title, product_price_sale, product_original_price, product_description;
    private RatingBar ratingBar;

    private Chip brand, category, backButton, btnAddtoCart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        recyclerView = findViewById(R.id.recyclerView);
        product_title = findViewById(R.id.product_title);
        brand = findViewById(R.id.brand);
        category = findViewById(R.id.category);
        backButton = findViewById(R.id.backButton);
//        btnAddtoCart = findViewById(R.id.btnAddtoCart);
        product_price_sale = findViewById(R.id.product_price_sale);
        product_original_price = findViewById(R.id.product_original_price);
        product_description = findViewById(R.id.product_description);
        ratingBar = findViewById(R.id.ratingBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        GetSingleProductRepository.setPhotoId(bundle.getInt("id"));
        ProductDetailViewModel productDetailViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
        productDetailViewModel.getProductDetail().observe(this, productDetail -> {
            imageUrls = getImageUrlsFromJson(productDetail.getImages());
            adapter = new ImageAdapter(imageUrls, this);
            recyclerView.setAdapter(adapter);

            product_title.setText(productDetail.getTitle());
            product_description.setText(productDetail.getDescription());
            product_original_price.setText("Before $" + productDetail.getPrice() + " - " + productDetail.getStock() + " stocks left ");
            product_price_sale.setText("Sale Price $" + computeSale(productDetail.getPrice(), productDetail.getDiscountPercentage()));
            ratingBar.setRating(productDetail.getRating());
            brand.setText(productDetail.getBrand());
            category.setText(productDetail.getCategory());
        });

        backButton.setOnClickListener(t -> {
            finish();
        });
        btnAddtoCart.setOnClickListener(t -> {

        });
    }

    private List<String> getImageUrlsFromJson(List<String> images) {
        List<String> urls = new ArrayList<>();
        urls.addAll(images);
        return urls;
    }
}