package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.controller.view.BillActivity;
import com.example.du_an_1.controller.view.CustomerActivity;
import com.example.du_an_1.controller.view.ProductActivity;

import com.example.du_an_1.databinding.FragmentHomeBinding;

import com.example.du_an_1.desgin_pattern.single_pantter.CartShopSingle;
import com.example.du_an_1.funtions.RequestPermissions;

import com.google.android.gms.ads.AdRequest;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding homeBinding;


    public HomeFragment() {
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
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);

        initView();
        return homeBinding.getRoot();
    }

    private void initView() {
        RequestPermissions.requestReadImgGalleryCamera(requireContext());
        AdRequest adRequest = new AdRequest.Builder().build();
        homeBinding.adView.loadAd(adRequest);
        homeBinding.adView2.loadAd(adRequest);

        CartShopSingle cartShopSingle = CartShopSingle.getInstance();

        if (cartShopSingle.getProducts().size() == 0){
            homeBinding.cartSize.setVisibility(View.GONE);
        }else{
            homeBinding.cartSize.setVisibility(View.VISIBLE);
            homeBinding.cartSize.setText(cartShopSingle.getProducts().size() + "");
        }

        homeBinding.shortcut.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductActivity.class);
                intent.putExtra("product", "addProduct");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.createBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), BillActivity.class);
                intent.putExtra("bill", "AddBill");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CustomerActivity.class);
                intent.putExtra("customer", "addCustomer");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.customerManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CustomerActivity.class);
                intent.putExtra("customer", "listCustomer");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.customerCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CustomerActivity.class);
                intent.putExtra("customer", "categoryCustomer");
                startActivity(intent);
            }
        });

        homeBinding.iconCartShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), BillActivity.class);
                intent.putExtra("bill", "AddBill");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.productCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductActivity.class);
                intent.putExtra("product", "categoryProduct");
                startActivity(intent);
            }
        });
    }
}