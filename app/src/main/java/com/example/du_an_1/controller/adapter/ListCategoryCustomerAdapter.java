package com.example.du_an_1.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.databinding.ItemCategoryCustomerBinding;
import com.example.du_an_1.model.Customer;

import java.util.ArrayList;

public class ListCategoryCustomerAdapter extends RecyclerView.
        Adapter<ListCategoryCustomerAdapter.ListCategoryCustomerViewHolder>{
    private ArrayList<Customer>listCus;

    public ListCategoryCustomerAdapter(ArrayList<Customer> listCus) {
        this.listCus = listCus;
    }
    public void updateCategoryCus(ArrayList<Customer> newList){
        this.listCus=newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCategoryCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryCustomerBinding itemCategoryCustomerBinding= ItemCategoryCustomerBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListCategoryCustomerViewHolder(itemCategoryCustomerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoryCustomerViewHolder holder, int position) {
        Customer customer=listCus.get(position);
        if (customer== null){
            return;
        }
        holder.binding.NameCategoryCustomer.setText(customer.getCustomerType());
    }

    @Override
    public int getItemCount() {
        if (listCus!= null){
            return listCus.size();
        }
        return 0;
    }

    class ListCategoryCustomerViewHolder extends RecyclerView.ViewHolder{

        ItemCategoryCustomerBinding binding;
        public ListCategoryCustomerViewHolder(@NonNull ItemCategoryCustomerBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
