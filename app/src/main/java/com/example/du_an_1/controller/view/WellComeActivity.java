package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.du_an_1.databinding.ActivityWellComeBinding;

public class WellComeActivity extends AppCompatActivity {

    private ActivityWellComeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityWellComeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);

        chuyenman();
    }

    private void chuyenman() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WellComeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}