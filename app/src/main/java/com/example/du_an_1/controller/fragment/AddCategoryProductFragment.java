package com.example.du_an_1.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.du_an_1.R;
import com.example.du_an_1.database.CategoryProductDao;
import com.example.du_an_1.databinding.FragmentAddCategoryProductBinding;
import com.example.du_an_1.desgin_pattern.build_pantter.CategoryProductBuilder;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.IdGenerator;
import com.example.du_an_1.funtions.MyFragment;
import com.example.du_an_1.funtions.Validations;
import com.example.du_an_1.model.CategoryProduct;
import com.example.du_an_1.model.Employee;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


public class AddCategoryProductFragment extends Fragment {
    private static final String COUNT_KEY="count_key";


    private FragmentAddCategoryProductBinding categoryProductBinding;
    public AddCategoryProductFragment() {
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
        categoryProductBinding = FragmentAddCategoryProductBinding.inflate(inflater,container,false);
        initView();
        return categoryProductBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        categoryProductBinding.adView.loadAd(adRequest);

        categoryProductBinding.btnSaveACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addCatePro();
            }
        });

        categoryProductBinding.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCatePro();
            }
        });
        categoryProductBinding.imgBackACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragment.backFragment(requireActivity().getSupportFragmentManager(),
                        R.id.fragmentAddPro,
                        new CategoryProductFragment(),
                        true);
            }
        });

    }

    private void addCatePro() {
        String nameCategoryProduct=categoryProductBinding.edtTenDanhMuc.getText().toString();
        String edtNote=categoryProductBinding.edtGhiChu.getText().toString();

        if (!Validations.isEmptyPress(categoryProductBinding.edtTenDanhMuc)){
            String mID=generateNextID(getContext(), "LSP_");
            CategoryProductBuilder categoryProductBuilder=new CategoryProductBuilder();
            CategoryProduct categoryProduct=categoryProductBuilder.addName(nameCategoryProduct)
                    .addNote(edtNote)
                    .addId(mID)
                    .build();

            AccountSingle accountSingle=AccountSingle.getInstance();
            Employee employee= accountSingle.getAccount();
            CategoryProductDao.insertCategoryProduct(categoryProduct, employee.getIdShop());
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
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