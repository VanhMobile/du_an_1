package com.example.du_an_1.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentAccountBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.model.Employee;


public class AccountFragment extends Fragment {

    Employee employee = AccountSingle.getInstance().getAccount();
    FragmentAccountBinding accountBinding;

    public AccountFragment() {
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
        accountBinding=FragmentAccountBinding.inflate(inflater, container, false);
        initView();
        return accountBinding.getRoot();
    }

    private void initView() {

    }
}