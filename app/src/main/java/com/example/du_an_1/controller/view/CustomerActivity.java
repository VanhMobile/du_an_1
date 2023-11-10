package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.fragment.Fragment_add_customer;
import com.example.du_an_1.controller.fragment.Fragment_list_customers;
import com.example.du_an_1.controller.fragment.ListCategoryCustomerFragment;
import com.example.du_an_1.funtions.MyFragment;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("customer");
        if (message != null){
            switch (message){
                case "listCustomer":
                    MyFragment.replaceFragment(CustomerActivity.this.getSupportFragmentManager()
                            , R.id.fragmentCustomer
                            , new Fragment_list_customers()
                            , true);
                    break;
                case "categoryCustomer":
                    MyFragment.replaceFragment(CustomerActivity.this.getSupportFragmentManager()
                            , R.id.fragmentCustomer
                            , new ListCategoryCustomerFragment()
                            , true);
                    break;
                case "addCustomer":
                    MyFragment.replaceFragment(CustomerActivity.this.getSupportFragmentManager()
                            , R.id.fragmentCustomer
                            , new Fragment_add_customer()
                            , true);
                    break;
            }
        }
    }
}