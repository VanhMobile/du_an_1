package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.controller.adapter.ListCategoryProductAdapter;
import com.example.du_an_1.database.ProductDao;
import com.example.du_an_1.databinding.FragmentCategoryProductBinding;
import com.example.du_an_1.funtions.MyFragment;
import com.example.du_an_1.model.Product;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


public class CategoryProductFragment extends Fragment {

    private FragmentCategoryProductBinding cateProBinding;
    private ListCategoryProductAdapter adapter;


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

        ProductDao.getProducts("Shop_1", new ProductDao.GetData() {
            @Override
            public void getData(ArrayList<Product> products) {
                if (adapter== null){
                    adapter=new ListCategoryProductAdapter(products);
                    cateProBinding.rcvCatePro.setAdapter(adapter);
                }else {
                    adapter.updateCatePro(products);
                }
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
    }
}