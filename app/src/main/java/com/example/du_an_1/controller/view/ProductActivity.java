package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.fragment.AddProductFragment;
import com.example.du_an_1.controller.fragment.CategoryProductFragment;
import com.example.du_an_1.funtions.MyFragment;

public class ProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("product");
        if (message != null){
            switch (message){
                case "addProduct":
                    MyFragment.replaceFragment(ProductActivity.this.getSupportFragmentManager()
                            , R.id.fragmentAddPro
                            , new AddProductFragment()
                            , true);
                    break;
                case "categoryProduct":
                    MyFragment.replaceFragment(ProductActivity.this.getSupportFragmentManager()
                            , R.id.fragmentAddPro
                            , new CategoryProductFragment()
                            , true);
                    break;
            }
        }
    }
}