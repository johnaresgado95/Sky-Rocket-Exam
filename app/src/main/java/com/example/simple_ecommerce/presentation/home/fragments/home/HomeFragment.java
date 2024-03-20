package com.example.simple_ecommerce.presentation.home.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.simple_ecommerce.R;
import com.example.simple_ecommerce.presentation.dialog.ErrorDialog;
import com.example.simple_ecommerce.presentation.searchproduct.SearchActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel productsViewModel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeAdapter homeAdapter;
    private ShimmerFrameLayout shimmerLayout;
    private RelativeLayout searchBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeContainer);
        recyclerView = view.findViewById(R.id.recycler_view_photos);
        searchBtn = view.findViewById(R.id.searchBtn);
        shimmerLayout = view.findViewById(R.id.shimmer_view_support);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shimmerLayout.startShimmer();
        homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);

        productsViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        productsViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            homeAdapter.setProducts(this.getContext(), products.getProducts());
            shimmerLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            shimmerLayout.stopShimmer();
        });

        productsViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            ErrorDialog errorDialog=new ErrorDialog(this.getActivity(), errorMessage);
            errorDialog.show();
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            shimmerLayout.startShimmer();
            shimmerLayout.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(null);
            homeAdapter = new HomeAdapter();
            recyclerView.setAdapter(homeAdapter);

            productsViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
            productsViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
                homeAdapter.setProducts(this.getContext(), products.getProducts());
                shimmerLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                shimmerLayout.stopShimmer();
            });
            swipeRefreshLayout.setRefreshing(false);
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        searchBtn.setOnClickListener(t -> {
            Intent i = new Intent(view.getContext(), SearchActivity.class);
            view.getContext().startActivity(i);
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}