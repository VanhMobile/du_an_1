package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.adapter.ListCustomerAdapter;
import com.example.du_an_1.database.CustomerDao;
import com.example.du_an_1.databinding.FragmentListCustomersBinding;
import com.example.du_an_1.funtions.MyFragment;
import com.example.du_an_1.model.Customer;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;


public class Fragment_list_customers extends Fragment {

    private FragmentListCustomersBinding listCustomerBinding;
    ListCustomerAdapter adapterCus;
    ArrayList<Customer>listCus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listCustomerBinding = FragmentListCustomersBinding.inflate(inflater,container,false);
        initView();

        return listCustomerBinding.getRoot();
    }

    private void initView() {

        AdRequest adRequest = new AdRequest.Builder().build();
        listCustomerBinding.adView.loadAd(adRequest);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        listCustomerBinding.rcvCustomers.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(listCustomerBinding.getRoot().getContext(),
                DividerItemDecoration.VERTICAL);
        listCustomerBinding.rcvCustomers.addItemDecoration(dividerItemDecoration);

        CustomerDao.getCustomers("Shop_1", new CustomerDao.GetData() {
            @Override
            public void getData(ArrayList<Customer> customers) {
                if (adapterCus== null){
                    adapterCus=new ListCustomerAdapter(customers);
                    listCustomerBinding.rcvCustomers.setAdapter(adapterCus);
                }else {
                    adapterCus.updateData(customers);
                }
            }
        });

        listCustomerBinding.edtSearchCus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


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