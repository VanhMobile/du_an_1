package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.fragment.CreateBillFragment;
import com.example.du_an_1.funtions.MyFragment;

public class BillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        initView();
    }

    private void initView() {
        MyFragment.replaceFragment(BillActivity.this.getSupportFragmentManager()
                , R.id.fragmentBill
                , new CreateBillFragment()
                , true);
    }
}