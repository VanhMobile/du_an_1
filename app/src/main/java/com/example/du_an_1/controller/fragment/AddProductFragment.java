package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentAddProductBinding;
import com.google.android.gms.ads.AdRequest;

public class AddProductFragment extends Fragment {

    private FragmentAddProductBinding productBinding;

    public AddProductFragment() {
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
        productBinding = FragmentAddProductBinding.inflate(inflater,container,false);
        initView();
        return productBinding.getRoot();
    }

    private void initView() {

    }
}