package com.example.du_an_1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentCategoryProductBinding;
import com.example.du_an_1.funtions.MyFragment;


public class CategoryProductFragment extends Fragment {

    private FragmentCategoryProductBinding cateProBinding;


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