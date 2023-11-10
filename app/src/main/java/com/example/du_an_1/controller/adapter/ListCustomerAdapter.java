package com.example.du_an_1.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.database.CustomerDao;
import com.example.du_an_1.databinding.ItemListCustomerBinding;
import com.example.du_an_1.model.Customer;

import java.util.ArrayList;

public class ListCustomerAdapter extends RecyclerView.Adapter<ListCustomerAdapter.ListCustomerViewHolder>{
    ArrayList<Customer>listCus;

    public ListCustomerAdapter(ArrayList<Customer> listCus) {
        this.listCus = listCus;
    }
    public void updateData(ArrayList<Customer> newList) {
        this.listCus = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListCustomerBinding binding=ItemListCustomerBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false);
        return new ListCustomerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCustomerViewHolder holder, int position) {
        Customer customer=listCus.get(position);
        if (customer==null){
            return;
        }
        holder.binding.tvNameCus.setText(customer.getCustomerName());
        holder.binding.tvNumberPhoneCus.setText(customer.getNumberPhone());
        holder.binding.tvAdressCus.setText(customer.getAddress());

    }

    @Override
    public int getItemCount() {
        if (listCus != null){
            return listCus.size();
        }
        return 0;
    }

    class ListCustomerViewHolder extends RecyclerView.ViewHolder{

        private ItemListCustomerBinding binding;
        public ListCustomerViewHolder(@NonNull ItemListCustomerBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
