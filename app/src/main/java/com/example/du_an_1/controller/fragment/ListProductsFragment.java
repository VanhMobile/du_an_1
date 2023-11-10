package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.view.ProductActivity;
import com.example.du_an_1.databinding.FragmentListProductsBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

public class ListProductsFragment extends Fragment {

    private FragmentListProductsBinding productsBinding;

    public ListProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productsBinding = FragmentListProductsBinding.inflate(inflater,container,false);
        initView();
        return productsBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        productsBinding.adView.loadAd(adRequest);
        productsBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductActivity.class);
                intent.putExtra("product","addProduct");
                startActivity(intent);
            }
        });
    }
}