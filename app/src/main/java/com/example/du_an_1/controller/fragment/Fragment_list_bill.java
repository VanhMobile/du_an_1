package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.databinding.FragmentListBillBinding;


public class Fragment_list_bill extends Fragment {

    private FragmentListBillBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBillBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}