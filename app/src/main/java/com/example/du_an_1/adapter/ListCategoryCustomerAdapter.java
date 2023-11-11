package com.example.du_an_1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.databinding.ItemCategoryCustomerBinding;
import com.example.du_an_1.model.CategoryCustomer;
import com.example.du_an_1.model.Customer;

import java.util.ArrayList;

public class ListCategoryCustomerAdapter extends RecyclerView.
        Adapter<ListCategoryCustomerAdapter.ListCategoryCustomerViewHolder>{
    private ArrayList<CategoryCustomer>listCateCus=new ArrayList<>();

    public ListCategoryCustomerAdapter(ArrayList<CategoryCustomer> listCateCus) {
        this.listCateCus = listCateCus;
    }
    public void updateCategoryCus(ArrayList<CategoryCustomer> newList){
        this.listCateCus=newList;
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
        CategoryCustomer categoryCustomer=listCateCus.get(position);
        if (categoryCustomer== null){
            return;
        }
        holder.binding.NameCategoryCustomer.setText(categoryCustomer.getNameCategory());
    }

    @Override
    public int getItemCount() {
        if (listCateCus != null){
            return listCateCus.size();
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
