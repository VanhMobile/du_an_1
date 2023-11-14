package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.ListCategoryCustomerAdapter;
import com.example.du_an_1.database.CategoryCustomerDao;
import com.example.du_an_1.database.CustomerDao;
import com.example.du_an_1.databinding.FragmentListCategoryCustomerBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.funtions.MyFragment;
import com.example.du_an_1.model.CategoryCustomer;
import com.example.du_an_1.model.Customer;
import com.example.du_an_1.model.Employee;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;


public class ListCategoryCustomerFragment extends Fragment {

    private FragmentListCategoryCustomerBinding customerBinding;
    private ListCategoryCustomerAdapter adapter;

    AccountSingle accountSingle=AccountSingle.getInstance();
    Employee employee= accountSingle.getAccount();

    public ListCategoryCustomerFragment() {
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
        customerBinding = FragmentListCategoryCustomerBinding.inflate(inflater,container,false);
        initView();
        return customerBinding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();

        customerBinding.adView.loadAd(adRequest);

        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        customerBinding.recListCategoryCus.setLayoutManager(manager);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration
                (customerBinding.getRoot().getContext(),DividerItemDecoration.VERTICAL);
        customerBinding.recListCategoryCus.addItemDecoration(dividerItemDecoration);

        reaLoad();

        customerBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reaLoad();
                customerBinding.swipeRefresh.setRefreshing(false);
            }
        });
        customerBinding.edtSearch.addTextChangedListener(new TextWatcher() {
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

        customerBinding.addCateCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFragment.replaceFragment(requireActivity().getSupportFragmentManager()
                        , R.id.fragmentCustomer
                        , new AddCategoryCustomerFragment()
                        , true);
            }
        });
        customerBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            }
        });
    }

    private void reaLoad() {
        CategoryCustomerDao.getCategoryCustomers(employee.getIdShop(), new CategoryCustomerDao.GetData() {
            @Override
            public void getData(ArrayList<CategoryCustomer> categoryCustomers) {
                if (adapter== null){
                    adapter=new ListCategoryCustomerAdapter(categoryCustomers);
                    customerBinding.recListCategoryCus.setAdapter(adapter);
                }else {
                    adapter.updateCategoryCus(categoryCustomers);
                }
            }
        });
    }
}