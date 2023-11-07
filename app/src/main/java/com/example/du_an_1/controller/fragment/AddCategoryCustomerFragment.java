package com.example.du_an_1.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentAddCategoryCustomerBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


public class AddCategoryCustomerFragment extends Fragment {

    private FragmentAddCategoryCustomerBinding categoryCustomerBinding;
    public AddCategoryCustomerFragment() {
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
        // Inflate the layout for this fragment
        categoryCustomerBinding = FragmentAddCategoryCustomerBinding.inflate(inflater,container,false);
        initView();
        return categoryCustomerBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        categoryCustomerBinding.adView.loadAd(adRequest);
    }
}