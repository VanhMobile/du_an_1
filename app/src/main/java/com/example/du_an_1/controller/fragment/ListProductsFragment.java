package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.adapter.CateProductDialogAdapter;
import com.example.du_an_1.adapter.ListProductAdapter;
import com.example.du_an_1.controller.view.ProductActivity;
import com.example.du_an_1.database.CategoryProductDao;
import com.example.du_an_1.database.ProductDao;
import com.example.du_an_1.databinding.BottomDialogFilterProBinding;
import com.example.du_an_1.databinding.FragmentListProductsBinding;
import com.example.du_an_1.model.CategoryProduct;
import com.example.du_an_1.model.Product;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListProductsFragment extends Fragment {

    private FragmentListProductsBinding productsBinding;
    ListProductAdapter productAdapter;

    public ListProductsFragment() {
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
        productsBinding = FragmentListProductsBinding.inflate(inflater,container,false);
        initView();
        return productsBinding.getRoot();
    }

    private void initView() {

        AdRequest adRequest = new AdRequest.Builder().build();

        productsBinding.adView.loadAd(adRequest);
        productsBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductActivity.class);
                intent.putExtra("product", "addProduct");
                startActivity(intent);
            }
        });

        ProductDao.getProducts("Shop_1", new ProductDao.GetData() {
            @Override
            public void getData(ArrayList<Product> products) {
                productAdapter = new ListProductAdapter(products, new ListProductAdapter.Click() {
                    @Override
                    public void clickBtnAdd(Product product) {

                    }
                });

                productsBinding.recyclerListProducts.setAdapter(productAdapter);
                productsBinding.recyclerListProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
                productsBinding.edtSearchProduct.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        productAdapter.filter(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                productsBinding.tvFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDiaLogCatePro(products);
                    }
                });
            }
        });
    }

    private void showDiaLogCatePro(ArrayList<Product> products) {
        BottomDialogFilterProBinding filterProBinding = BottomDialogFilterProBinding.inflate(getLayoutInflater());
        BottomSheetDialog bottomFilter= new BottomSheetDialog(requireContext(),R.style.BottomSheetDialogThem);
        bottomFilter.setContentView(filterProBinding.getRoot());

        filterProBinding.filterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productAdapter.setData(products);
                productsBinding.tvFilter.setText("Tất cả");
                bottomFilter.dismiss();
            }
        });

        CategoryProductDao.getCategoryProduct("Shop_1", new CategoryProductDao.GetData() {
            @Override
            public void getData(ArrayList<CategoryProduct> categoryProducts) {
                CateProductDialogAdapter adapter = new CateProductDialogAdapter(categoryProducts, new CateProductDialogAdapter.Click() {
                    @Override
                    public void click(String nameCatePro) {
                        ArrayList<Product> filterList = (ArrayList<Product>)  products.stream()
                                .filter(item -> item.getCate().equals(nameCatePro))
                                .collect(Collectors.toList());
                        productAdapter.setData(filterList);
                        productsBinding.tvFilter.setText(nameCatePro);
                        bottomFilter.dismiss();
                    }
                });
                filterProBinding.reyCateProductDialog.setAdapter(adapter);
                filterProBinding.reyCateProductDialog.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });

        bottomFilter.show();
    }
}