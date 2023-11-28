package com.example.du_an_1.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.view.LoginActivity;
import com.example.du_an_1.database.EmployeeDao;
import com.example.du_an_1.databinding.DialogUpdatePasswordBinding;
import com.example.du_an_1.databinding.FragmentAccountBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.Employee;

import java.util.ArrayList;


public class AccountFragment extends Fragment {

    Employee employee = AccountSingle.getInstance().getAccount();
    FragmentAccountBinding accountBinding;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        accountBinding=FragmentAccountBinding.inflate(inflater, container, false);
        initView();
        return accountBinding.getRoot();
    }

    private void initView() {
        accountBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        accountBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdatePasswordBinding updatePasswordBinding = DialogUpdatePasswordBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(updatePasswordBinding.getRoot());
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                updatePasswordBinding.passwordOld.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equals(employee.getPassword())){
                            updatePasswordBinding.errorMass.setVisibility(View.GONE);
                        }else{
                            updatePasswordBinding.errorMass.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                Validations.isPassword(updatePasswordBinding.passwordNew,updatePasswordBinding.errorMass2);
                updatePasswordBinding.btnUpDataPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = 0;
                        if (updatePasswordBinding.passwordOld.getText().toString().trim().equals(employee.getPassword())){
                            updatePasswordBinding.errorMass.setVisibility(View.GONE);
                        }else{
                            updatePasswordBinding.errorMass.setVisibility(View.VISIBLE);
                            count ++;
                        }

                        if (!Validations.isPasswordPress(updatePasswordBinding.passwordNew,updatePasswordBinding.errorMass2)){
                            count ++;
                        }

                        if (count != 0){
                            return;
                        }

                        employee.setPassword(updatePasswordBinding.passwordNew.getText().toString().trim());
                        EmployeeDao.insertEmployee(employee);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        accountBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn cập nhập lại thông tin cá nhân ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        upDataInforEmployee();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        accountBinding.nameEmploy.setText(employee.getName());
        accountBinding.emailEmploy.setText(employee.getEmail());
        accountBinding.phoneNumberEmploy.setText(employee.getNumberPhone());
    }

    private void upDataInforEmployee() {
        EmployeeDao.getEmployees(employee.getIdShop(), new EmployeeDao.GetData() {
            @Override
            public void getData(ArrayList<Employee> employees) {
                int count = 0;

                if (Validations.isEmptyPress(accountBinding.nameEmploy)){
                    count ++;
                }

                if (!Validations.isEmptyPress(accountBinding.emailEmploy)){
                    if (!Validations.isEmailPress(accountBinding.emailEmploy)){
                        count ++;
                    }
                }else{
                    count ++;
                }

                if (!Validations.isEmptyPress(accountBinding.phoneNumberEmploy)){
                    if (!Validations.isPhoneNumberPress(accountBinding.phoneNumberEmploy)){
                        count ++;
                    }
                }else{
                    count ++;
                }

                for (int i = 0; i < employees.size(); i++){
                    if (!employees.get(i).getIdEmployee().equals(employee.getIdEmployee())){
                        if (employees.get(i).getNumberPhone().equals(accountBinding.phoneNumberEmploy.getText().toString().trim())){
                            count++;
                            Toast.makeText(requireActivity(),"Số điện thoại đã có",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                for (int i = 0; i < employees.size(); i++){
                    if (!employees.get(i).getIdEmployee().equals(employee.getIdEmployee())){
                        if (employees.get(i).getEmail().equals(accountBinding.emailEmploy.getText().toString())){
                            count++;
                            Toast.makeText(requireActivity(),"Email đã có",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if (count != 0){
                    return;
                }

                String nameEmployee = accountBinding.nameEmploy.getText().toString().trim();
                String emailShop = accountBinding.emailEmploy.getText().toString().trim();
                String numberPhone = accountBinding.phoneNumberEmploy.getText().toString().trim();

                employee.setName(nameEmployee);
                employee.setEmail(emailShop);
                employee.setNumberPhone(numberPhone);

                EmployeeDao.insertEmployee(employee);
            }
        });
    }

}