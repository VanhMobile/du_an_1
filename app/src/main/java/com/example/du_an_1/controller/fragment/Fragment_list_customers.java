package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentListCustomersBinding;
import com.example.du_an_1.funtions.MyFragment;


public class Fragment_list_customers extends Fragment {

    private FragmentListCustomersBinding listCustomerBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listCustomerBinding = FragmentListCustomersBinding.inflate(inflater,container,false);
        initView();
        return listCustomerBinding.getRoot();
    }

    private void initView() {
        listCustomerBinding.addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFragment.replaceFragment(requireActivity().getSupportFragmentManager()
                        , R.id.fragmentCustomer
                        , new Fragment_add_customer()
                        , true);
            }
        });
    }
}