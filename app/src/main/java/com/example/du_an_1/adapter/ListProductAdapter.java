package com.example.du_an_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.ItemListProductsBinding;
import com.example.du_an_1.funtions.MoneyFormat;
import com.example.du_an_1.model.Product;

import java.util.ArrayList;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ViewHolder> {

    private ArrayList<Product> products;

    private ArrayList<Product> filteredList;

    Click click;

    public ListProductAdapter(ArrayList<Product> products, Click click) {
        this.products = products;
        this.click = click;
        this.filteredList = new ArrayList<>(products);
    }

    public void setData(ArrayList<Product> products){
        this.filteredList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListProductsBinding productsBinding = ItemListProductsBinding.inflate(
                LayoutInflater.from(parent.getContext())
                , parent
                , false);
        return new ViewHolder(productsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = filteredList.get(position);
        int index = position + 1;
        holder.productsBinding.nameProduct.setText(index + "." + product.getProductName());
        holder.productsBinding.wholesalePrice.setText("Giá sỉ: " + MoneyFormat.moneyFormat(product.getWholeSalePrice()));
        holder.productsBinding.priceProduct.setText(MoneyFormat.moneyFormat(product.getRetailPrice()));
        holder.productsBinding.quantityProduct.setText("Tồn kho: " + product.getQuantity());
        Glide.with(holder.productsBinding.imgProduct.getContext())
                .load(product.getImgPath())
                .error(R.drawable.product_img)
                .into(holder.productsBinding.imgProduct);

        holder.productsBinding.btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.clickBtnAdd(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListProductsBinding productsBinding;

        public ViewHolder(@NonNull ItemListProductsBinding productsBinding) {
            super(productsBinding.getRoot());
            this.productsBinding = productsBinding;
        }
    }

    public interface Click {
        void clickBtnAdd(Product product);
    }

    public void filter(String s) {
        filteredList.clear();
        if (s.isEmpty()) {
            filteredList.addAll(products);
        } else {
            products.forEach(o -> {
                if (o.getProductName().toLowerCase().contains(s.toLowerCase()) || o.getCate().toLowerCase().contains(s.toLowerCase())){
                    filteredList.add(o);
                }
            });
        }
        notifyDataSetChanged();
    }

}
