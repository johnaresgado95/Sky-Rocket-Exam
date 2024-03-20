package com.example.simple_ecommerce.presentation.searchproduct.adapter;

import static com.example.simple_ecommerce.data.util.DisplayUtil.computeSale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.data.dto.response.Product;
import com.example.simple_ecommerce.presentation.home.fragments.home.HomeAdapter;
import com.example.simple_ecommerce.presentation.productdetail.ProductDetailActivity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private static Context context;
    private static List<Product> products;

    @SuppressLint("NotifyDataSetChanged")
    public void setProducts(Context context, List<Product> products) {
        SearchAdapter.context = context;
        SearchAdapter.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new SearchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getThumbnail())
                .into(holder.product_image);

        holder.product_original_price.setText("Before $" + product.getPrice() + " - " + product.getStock() + " stocks left ");
        holder.product_price_sale.setText("Now $" + computeSale(product.getPrice(), product.getDiscountPercentage()));
        holder.product_description.setText(product.getTitle());
        holder.ratingBar.setRating(product.getRating());
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView product_original_price, product_description, product_price_sale;
        ImageView product_image;
        CardView product_view;
        RatingBar ratingBar;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            product_original_price = itemView.findViewById(R.id.product_original_price);
            product_price_sale = itemView.findViewById(R.id.product_price_sale);
            product_description = itemView.findViewById(R.id.product_description);
            product_image = itemView.findViewById(R.id.product_image);
            product_view = itemView.findViewById(R.id.product_card);
            ratingBar = itemView.findViewById(R.id.ratingBar);

            product_view.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            Product product = products.get(getAdapterPosition());
            Intent i = new Intent(context, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", product.getId());
            i.putExtras(bundle);
            context.startActivity(i);
        }
    }

}
