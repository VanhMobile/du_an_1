package com.example.du_an_1.controller.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.view.LoginActivity;
import com.example.du_an_1.databinding.FragmentAccountBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.model.Employee;


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
                        resetData();
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

        accountBinding.updatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        accountBinding.nameEmploy.setText(employee.getName());
        accountBinding.emailEmploy.setText(employee.getEmail());
        accountBinding.phoneNumberEmploy.setText(employee.getNumberPhone());
    }
    private void resetData(){
        accountBinding.nameEmploy.setText("");
        accountBinding.emailEmploy.setText("");
        accountBinding.phoneNumberEmploy.setText("");
    }
}