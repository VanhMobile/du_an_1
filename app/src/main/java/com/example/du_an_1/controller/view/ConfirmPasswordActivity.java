package com.example.du_an_1.controller.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;

import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.du_an_1.database.EmployeeDao;
import com.example.du_an_1.databinding.ActivityConfrimPasswordBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.Employee;


import java.util.ArrayList;
import java.util.Random;


public class ConfirmPasswordActivity extends AppCompatActivity {

    public static final String TAG = ConfirmPasswordActivity.class.getName();
    private ActivityConfrimPasswordBinding confrimPasswordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        confrimPasswordBinding = ActivityConfrimPasswordBinding.inflate(getLayoutInflater());
        setContentView(confrimPasswordBinding.getRoot());


        confrimPasswordBinding.btnBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Validations.isPhoneNumber(confrimPasswordBinding.edtNumberPhone);
        confrimPasswordBinding.btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeDao.getEmployees(new EmployeeDao.GetData() {
                    @Override
                    public void getData(ArrayList<Employee> employees) {
                        int check = 0;
                        if (!Validations.isEmptyPress(confrimPasswordBinding.edtNumberPhone)) {
                            if (!Validations.isPhoneNumberPress(confrimPasswordBinding.edtNumberPhone)) {
                                check++;
                            }
                        } else {
                            check++;
                        }
                        for (Employee item : employees) {
                            if (item.getNumberPhone().equals(confrimPasswordBinding.edtNumberPhone.getText().toString())) {

                                if (ContextCompat.checkSelfPermission(ConfirmPasswordActivity.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                                    sendOtp();
                                    AccountSingle.getInstance().setAccount(item);
                                } else {
                                    ActivityCompat.requestPermissions(ConfirmPasswordActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
                                }

                            }
                        }
                    }
                });
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOtp();
            }
        }
    }

    private void sendOtp() {
        int min = 100000;
        int max = 999999;
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        String otp = String.valueOf(randomNumber);
        String phone = confrimPasswordBinding.edtNumberPhone.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage("Mã otp của bạn là: " + otp);
        smsManager.sendMultipartTextMessage(phone, null, parts, null, null);
        Intent intent = new Intent(this, OtpConfirmActivity.class);
        intent.putExtra("otp", otp);
        startActivity(intent);
    }

}