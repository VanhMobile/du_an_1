package com.example.du_an_1.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.databinding.ItemCategoryProductBinding;
import com.example.du_an_1.model.Product;

import java.util.ArrayList;

public class ListCategoryProductAdapter extends RecyclerView.
        Adapter<ListCategoryProductAdapter.ListCategoryProductViewHolder>{

    private ArrayList<Product>listProduct;

    public ListCategoryProductAdapter(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void updateCatePro(ArrayList<Product> newList){
        this.listProduct=newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCategoryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryProductBinding itemCategoryProductBinding=ItemCategoryProductBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListCategoryProductViewHolder(itemCategoryProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoryProductViewHolder holder, int position) {
        Product product=listProduct.get(position);
        if (product== null){
            return;
        }
        holder.itemCategoryProductBinding.tvCategoryProduct.setText(product.getProperties());
    }

    @Override
    public int getItemCount() {
        if (listProduct!= null){
            return listProduct.size();
        }
        return 0;
    }

    class ListCategoryProductViewHolder extends RecyclerView.ViewHolder{


        private ItemCategoryProductBinding itemCategoryProductBinding;
        public ListCategoryProductViewHolder(@NonNull ItemCategoryProductBinding binding){
            super(binding.getRoot());
            this.itemCategoryProductBinding=binding;
        }
    }
}
