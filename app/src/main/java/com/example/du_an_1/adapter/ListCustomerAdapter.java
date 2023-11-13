package com.example.du_an_1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.databinding.ItemListCustomerBinding;
import com.example.du_an_1.model.Customer;

import java.util.ArrayList;

public class ListCustomerAdapter extends RecyclerView.Adapter<ListCustomerAdapter.ListCustomerViewHolder>{
    ArrayList<Customer>listCus;
    ArrayList<Customer>filterList;

    public ListCustomerAdapter(ArrayList<Customer> listCus) {
        this.listCus = listCus;
        this.filterList=new ArrayList<>(listCus);
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
    public void filter(String s){
        listCus.clear();
        if (s.isEmpty()){
            listCus.addAll(filterList);
        }else {
            for (Customer item: filterList) {
                if (item.getAddress().contains(s)|| item.getNumberPhone().contains(s)||
                        item.getCustomerName().contains(s)){
                    listCus.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}
