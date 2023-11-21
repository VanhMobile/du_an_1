package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.ListCategoryProductAdapter;
import com.example.du_an_1.database.CategoryProductDao;
import com.example.du_an_1.database.ProductDao;
import com.example.du_an_1.databinding.FragmentCategoryProductBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.MyFragment;
import com.example.du_an_1.model.CategoryProduct;
import com.example.du_an_1.model.Employee;
import com.example.du_an_1.model.Product;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;


public class CategoryProductFragment extends Fragment {

    private FragmentCategoryProductBinding cateProBinding;
    private ListCategoryProductAdapter adapter;
    AccountSingle accountSingle=AccountSingle.getInstance();
    Employee employee= accountSingle.getAccount();


    public CategoryProductFragment() {
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
        cateProBinding = FragmentCategoryProductBinding.inflate(inflater,container,false);
        initView();
        return cateProBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        cateProBinding.adView.loadAd(adRequest);

        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        cateProBinding.rcvCatePro.setLayoutManager(manager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration
                (cateProBinding.getRoot().getContext(), DividerItemDecoration.VERTICAL);
        cateProBinding.rcvCatePro.addItemDecoration(dividerItemDecoration);
        CategoryProductDao.getCategoryProduct(employee.getIdShop(), new CategoryProductDao.GetData() {
            @Override
            public void getData(ArrayList<CategoryProduct> categoryProducts) {
                if (adapter== null){
                    adapter=new ListCategoryProductAdapter(categoryProducts);
                    cateProBinding.rcvCatePro.setAdapter(adapter);
                }else {
                    adapter.updateCatePro(categoryProducts);
                }
            }
        });

        addRecyc();
        cateProBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addRecyc();
                cateProBinding.swipeRefresh.setRefreshing(false);
            }
        });
        cateProBinding.addCatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFragment.replaceFragment(requireActivity().getSupportFragmentManager()
                        , R.id.fragmentAddPro
                        , new AddCategoryProductFragment()
                        , true);
            }
        });
        cateProBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            }
        });
        cateProBinding.searchCategoryPro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addRecyc() {
        CategoryProductDao.getCategoryProduct(employee.getIdShop(), new CategoryProductDao.GetData() {
            @Override
            public void getData(ArrayList<CategoryProduct> categoryProducts) {
                adapter.updateCatePro(categoryProducts);
            }
        });
    }
}