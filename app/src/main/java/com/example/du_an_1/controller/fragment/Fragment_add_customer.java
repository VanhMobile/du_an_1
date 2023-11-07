package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentAddCustomerBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


public class Fragment_add_customer extends Fragment {

    private FragmentAddCustomerBinding customerBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        customerBinding = FragmentAddCustomerBinding.inflate(inflater,container,false);
        initView();
        return customerBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        customerBinding.adView.loadAd(adRequest);
    }
}