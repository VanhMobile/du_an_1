package com.example.du_an_1.controller.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.du_an_1.databinding.ActivityOtpConfirmBinding;
import com.example.du_an_1.funtions.Validations;


public class OtpConfirmActivity extends AppCompatActivity {

    ActivityOtpConfirmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent == null) return;
        String otp = intent.getStringExtra("otp");
        binding.btnSend.setOnClickListener(v -> {
            int check = 0;
            if (Validations.isEmptyPress(binding.edtOtp)) {
                check++;
            }
            if (check != 0){
                return;
            }
            if (binding.edtOtp.getText().toString().equals(otp)) {
                startActivity(new Intent(OtpConfirmActivity.this, NewPassActivity.class));
                finish();
            }else{
                Toast.makeText(OtpConfirmActivity.this, "Sai mã xác nhận", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpConfirmActivity.this, LoginActivity.class));
                finish();
            }
        });
    }


}