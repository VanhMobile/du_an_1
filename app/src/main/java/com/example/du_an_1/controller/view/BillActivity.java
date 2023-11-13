package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.fragment.CreateBillFragment;
import com.example.du_an_1.controller.fragment.DetailBillFragment;
import com.example.du_an_1.funtions.MyFragment;

public class BillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null){
            String message = intent.getStringExtra("bill");
            if (message != null){
                switch (message){
                    case "AddBill":
                        MyFragment.replaceFragment(BillActivity.this.getSupportFragmentManager()
                                , R.id.fragmentBill
                                , new CreateBillFragment()
                                , true);
                        break;
                    case "detailBill":
                        MyFragment.replaceFragment(BillActivity.this.getSupportFragmentManager()
                                , R.id.fragmentBill
                                , new DetailBillFragment()
                                , true);
                        break;
                }
            }
        }else{
            MyFragment.replaceFragment(BillActivity.this.getSupportFragmentManager()
                    , R.id.fragmentBill
                    , new CreateBillFragment()
                    , true);
        }
    }
}