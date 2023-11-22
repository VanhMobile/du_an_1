package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.du_an_1.adapter.ListBillAdapter;
import com.example.du_an_1.controller.view.BillActivity;
import com.example.du_an_1.database.BillDao;
import com.example.du_an_1.databinding.FragmentListBillBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.desgin_pattern.single_pantter.BillSingle;
import com.example.du_an_1.model.Bill;
import com.example.du_an_1.model.Employee;
import com.example.du_an_1.model.Product;
import com.google.android.gms.ads.AdRequest;


import java.util.ArrayList;


public class Fragment_list_bill extends Fragment {

    private FragmentListBillBinding binding;
    private ArrayList<Bill> billArrayList;
    Employee employee = AccountSingle.getInstance().getAccount();
    private ListBillAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBillBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        billArrayList = new ArrayList<>();
        BillDao.GetBills(employee.getIdShop(), new BillDao.GetData() {
            @Override
            public void getData(ArrayList<Bill> bills) {
                bills.forEach(o -> {
                    if (o.getIdAccount().equals(employee.getName()+"-"+employee.getNumberPhone())){
                        billArrayList.add(o);
                    }
                });

                adapter = new ListBillAdapter(billArrayList, new ListBillAdapter.Click() {
                    @Override
                    public void clickItem(Bill bill) {
                        Intent intent = new Intent(requireContext(), BillActivity.class);
                        intent.putExtra("bill", "detailBill");
                        BillSingle.getInstance().setBill(bill);
                        startActivity(intent);
                    }
                });
                // Áp dụng DividerItemDecoration cho RecyclerView
                if (isAdded()){
                    LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(),
                            layoutManager.getOrientation());
                    binding.recyclerViewListBill.addItemDecoration(dividerItemDecoration);
                    binding.recyclerViewListBill.setAdapter(adapter);
                    binding.recyclerViewListBill.setLayoutManager(layoutManager);
                }
                binding.searchView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.filterBill(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addReload();
                binding.swipeRefresh.setRefreshing(false);
            }
        });

        binding.btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), BillActivity.class);
                intent.putExtra("bill", "AddBill");
                startActivity(intent);
            }
        });
    }

    private void addReload() {
        billArrayList = new ArrayList<>();
        BillDao.GetBills(employee.getIdShop(), new BillDao.GetData() {
            @Override
            public void getData(ArrayList<Bill> bills) {
                bills.forEach(o -> {
                    if (o.getIdAccount().equals(employee.getIdEmployee())){
                        billArrayList.add(o);
                    }
                });

                adapter.setData(billArrayList);
            }
        });
    }
}