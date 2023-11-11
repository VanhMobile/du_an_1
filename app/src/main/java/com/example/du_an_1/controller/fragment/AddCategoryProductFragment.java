package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentAddCategoryProductBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


public class AddCategoryProductFragment extends Fragment {

    private FragmentAddCategoryProductBinding categoryProductBinding;
    public AddCategoryProductFragment() {
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
        categoryProductBinding = FragmentAddCategoryProductBinding.inflate(inflater,container,false);
        initView();
        return categoryProductBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        categoryProductBinding.adView.loadAd(adRequest);
    }
}