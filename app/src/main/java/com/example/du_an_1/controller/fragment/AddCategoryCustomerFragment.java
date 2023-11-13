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

import com.example.du_an_1.R;
import com.example.du_an_1.database.CategoryCustomerDao;
import com.example.du_an_1.databinding.FragmentAddCategoryCustomerBinding;
import com.example.du_an_1.desgin_pattern.build_pantter.CategoryCustomerBuilder;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.IdGenerator;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.CategoryCustomer;
import com.example.du_an_1.model.Employee;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


public class AddCategoryCustomerFragment extends Fragment {
    private static final String COUNT_KEY="count_key_cus";

    private FragmentAddCategoryCustomerBinding categoryCustomerBinding;
    public AddCategoryCustomerFragment() {
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
                String nameCategoryCus=categoryCustomerBinding.edtCategoryName.getText().toString();
                String mNote=categoryCustomerBinding.edtNote.getText().toString();

                if (!Validations.isEmptyPress(categoryCustomerBinding.edtCategoryName)){
                    String mID=generateNextID(getContext(), "LKH_");
                    CategoryCustomerBuilder categoryCustomerBuilder=new CategoryCustomerBuilder();
                    CategoryCustomer categoryCustomer= categoryCustomerBuilder
                            .addId(mID)
                            .addName(nameCategoryCus)
                            .addNote(mNote).build();

                    AccountSingle accountSingle=AccountSingle.getInstance();
                    Employee employee=accountSingle.getAccount();
                    CategoryCustomerDao.insertCategoryCustomer(categoryCustomer,employee.getIdShop());
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static String generateNextID(Context context, String s){
        int count=getNextCount(context);
        String id=s+ count;
        saveNextCount(context, count+1);
        return id;
    }

    private static int getNextCount(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(COUNT_KEY, 1);
    }

    private static void saveNextCount(Context context, int count){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(COUNT_KEY, count);
        editor.commit();
    }
}