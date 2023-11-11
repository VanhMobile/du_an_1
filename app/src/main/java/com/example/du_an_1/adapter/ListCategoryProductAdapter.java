package com.example.du_an_1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.database.CategoryProductDao;
import com.example.du_an_1.databinding.ItemCategoryProductBinding;
import com.example.du_an_1.model.CategoryProduct;
import com.example.du_an_1.model.Product;

import java.util.ArrayList;

public class ListCategoryProductAdapter extends RecyclerView.
        Adapter<ListCategoryProductAdapter.ListCategoryProductViewHolder>{

    private ArrayList<CategoryProduct>listCateProduct;

    public ListCategoryProductAdapter(ArrayList<CategoryProduct> listCateProduct) {
        this.listCateProduct = listCateProduct;
    }

    public void updateCatePro(ArrayList<CategoryProduct> newList){
        this.listCateProduct=newList;
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
        CategoryProduct categoryProduct =listCateProduct.get(position);
        if (categoryProduct== null){
            return;
        }
        holder.itemCategoryProductBinding.NameCategoryProduct.setText(categoryProduct.getNameCategory());
    }

    @Override
    public int getItemCount() {
        if (listCateProduct != null){
            return listCateProduct.size();
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
