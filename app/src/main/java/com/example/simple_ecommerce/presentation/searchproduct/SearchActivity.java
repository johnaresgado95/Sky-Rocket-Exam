package com.example.simple_ecommerce.presentation.searchproduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.domain.repository.GetSearchProductRepository;
import com.example.simple_ecommerce.presentation.dialog.ErrorDialog;
import com.example.simple_ecommerce.presentation.searchproduct.adapter.SearchAdapter;
import com.google.android.material.textfield.TextInputEditText;

public class SearchActivity extends AppCompatActivity {

    private SearchViewModel searchViewModel;
    private TextInputEditText ti_search;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private TextView product_empty;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ti_search = findViewById(R.id.ti_search);
        recyclerView = findViewById(R.id.recycler_view_search);
        product_empty = findViewById(R.id.product_empty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ti_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                GetSearchProductRepository.setQuery(String.valueOf(charSequence).trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ti_search.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                searchAdapter = new SearchAdapter();
                recyclerView.setAdapter(searchAdapter);
                searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
                searchViewModel.getErrorMessage().observe(this, errorMessage -> {
                    ErrorDialog errorDialog = new ErrorDialog(this, errorMessage);
                    errorDialog.show();
                });

                searchViewModel.getProductDetail().observe(this, products -> {
                    if (products.getTotal() != 0) {
                        searchAdapter.setProducts(this, products.getProducts());
                        recyclerView.setVisibility(View.VISIBLE);
                        product_empty.setVisibility(View.GONE);
                    } else {
                        product_empty.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        product_empty.setText("Search not matched, try other keyword. (ex: Macbook, Phone)");
                    }
                });
                return true;
            }
            return false;
        });
    }

}