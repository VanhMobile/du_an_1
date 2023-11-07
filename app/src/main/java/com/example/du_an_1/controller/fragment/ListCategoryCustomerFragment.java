package com.example.du_an_1.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.view.CustomerActivity;
import com.example.du_an_1.databinding.FragmentListCategoryCustomerBinding;
import com.example.du_an_1.funtions.MyFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


public class ListCategoryCustomerFragment extends Fragment {

    private FragmentListCategoryCustomerBinding customerBinding;

    public ListCategoryCustomerFragment() {
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
        customerBinding = FragmentListCategoryCustomerBinding.inflate(inflater,container,false);
        initView();
        return customerBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();

        customerBinding.adView.loadAd(adRequest);
        customerBinding.addCateCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFragment.replaceFragment(requireActivity().getSupportFragmentManager()
                        , R.id.fragmentCustomer
                        , new AddCategoryCustomerFragment()
                        , true);
            }
        });
    }
}