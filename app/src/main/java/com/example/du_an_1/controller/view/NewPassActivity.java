package com.example.du_an_1.controller.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.R;
import com.example.du_an_1.database.EmployeeDao;
import com.example.du_an_1.databinding.ActivityNewPassBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.Employee;

public class NewPassActivity extends AppCompatActivity {
    ActivityNewPassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {

        Validations.isPassword(binding.edtPass);
        Validations.isPassword(binding.edtPass2);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 0;
                if (!Validations.isEmptyPress(binding.edtPass)) {
                    if (!Validations.isPasswordPress(binding.edtPass)) {
                        check++;
                    }
                } else {
                    check++;
                }
                if (!Validations.isEmptyPress(binding.edtPass2)) {
                    if (!Validations.isPasswordPress(binding.edtPass2)) {
                        check++;
                    }
                } else {
                    check++;
                }

                if (!binding.edtPass.getText().toString().equals(binding.edtPass2.getText().toString())) {
                    binding.edtPass2.setError("Không trùng mật khẩu");
                    check++;
                } else {
                    binding.edtPass2.setError(null);
                }
                if (check != 0) {
                    return;
                }
                newpass();

            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewPassActivity.this, OtpConfirmActivity.class));
                finish();
            }
        });

    }

    private void newpass() {
        Employee employee = AccountSingle.getInstance().getAccount();
        String pass = binding.edtPass.getText().toString();
        employee.setPassword(pass);
        EmployeeDao.insertEmployee(employee);
        Toast.makeText(this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}