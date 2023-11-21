package com.example.du_an_1.controller.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.du_an_1.R;
import com.example.du_an_1.database.CategoryCustomerDao;
import com.example.du_an_1.databinding.FragmentAddCategoryCustomerBinding;
import com.example.du_an_1.desgin_pattern.build_pantter.CategoryCustomerBuilder;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.IdGenerator;
import com.example.du_an_1.funtions.MyFragment;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.CategoryCustomer;
import com.example.du_an_1.model.Employee;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


public class AddCategoryCustomerFragment extends Fragment {
    private static final String COUNT_KEY="count_key_cus";

    private FragmentAddCategoryCustomerBinding categoryCustomerBinding;
    public AddCategoryCustomerFragment() {
        // Required empty public constructor
    }
    AccountSingle accountSingle=AccountSingle.getInstance();
    Employee employee=accountSingle.getAccount();

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
        categoryCustomerBinding = FragmentAddCategoryCustomerBinding.inflate(inflater,container,false);
        initView();
        return categoryCustomerBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        categoryCustomerBinding.adView.loadAd(adRequest);

        categoryCustomerBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addCateCustomer();
            }
        });

        categoryCustomerBinding.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCateCustomer();
            }
        });
        categoryCustomerBinding.imgBackACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragment.backFragment(requireActivity().getSupportFragmentManager(),
                        R.id.fragmentCustomer,
                        new ListCategoryCustomerFragment(),
                        true);
            }
        });
    }

    private void addCateCustomer() {
        String nameCategoryCus=categoryCustomerBinding.edtCategoryName.getText().toString();
        String mNote=categoryCustomerBinding.edtNote.getText().toString();

        if (!Validations.isEmptyPress(categoryCustomerBinding.edtCategoryName)){
            CategoryCustomerDao.getCategoryCustomers(employee.getIdShop(), new CategoryCustomerDao.GetData() {
                @Override
                public void getData(ArrayList<CategoryCustomer> categoryCustomers) {
                    String mID = IdGenerator.generateNextShopId(categoryCustomers.size(), "KH_");
                    CategoryCustomerBuilder categoryCustomerBuilder=new CategoryCustomerBuilder();
                    CategoryCustomer categoryCustomer= categoryCustomerBuilder
                            .addId(mID)
                            .addName(nameCategoryCus)
                            .addNote(mNote).build();


                    CategoryCustomerDao.insertCategoryCustomer(categoryCustomer,employee.getIdShop());
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}