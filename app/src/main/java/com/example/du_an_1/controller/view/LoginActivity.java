package com.example.du_an_1.controller.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.MainActivity;
import com.example.du_an_1.database.EmployeeDao;
import com.example.du_an_1.databinding.ActivityLoginBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.MessengerManager;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.Employee;

import java.util.ArrayList;

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
        Validations.isEmpty(loginBinding.userName);
        Validations.isEmpty(loginBinding.password);

        loginBinding.btnMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessengerManager.openMessengerWithLink("https://www.facebook.com/messages/148593518345206",LoginActivity.this);
            }
        });

        loginBinding.btnLoginLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int count = 0;
                if (Validations.isEmptyPress(loginBinding.userName)){
                    count ++;
                }

                if (Validations.isEmptyPress(loginBinding.password)){
                    count ++;
                }

                if (count != 0){
                    return;
                }

                // sử lý logic login ở đây
                EmployeeDao.getEmployees(new EmployeeDao.GetData() {
                    // đọc dữ liệu từ fire base ra
                    @Override
                    public void getData(ArrayList<Employee> employees) {
                        String numberPhone = loginBinding.userName.getText().toString();
                        boolean checkLogin=false;

                        for (Employee item: employees) {
                            if ((item.getNumberPhone().equals(numberPhone))|| (item.getEmail().equals(numberPhone))){
                                AccountSingle.getInstance().setAccount(item);
                                Toast.makeText(LoginActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                                checkLogin=true;
                                break;
                            }
                        }
                        if (!checkLogin){
                            Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


}