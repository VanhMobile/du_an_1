package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.MainActivity;
import com.example.du_an_1.databinding.ActivityLoginBinding;
import com.example.du_an_1.funtions.Validations;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding loginBinding;
    EditText edtUserName, edtPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        initView();
    }

    private void initView() {
        Validations.isEmpty(edtUserName);
        Validations.isEmpty(edtPassWord);
        loginBinding.btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Validations.isEmptyPress(edtUserName) && !Validations.isEmptyPress(edtPassWord)) {

                }
            }
        });
    }


}