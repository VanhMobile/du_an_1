package com.example.du_an_1.database;

import android.util.Log;

import androidx.annotation.NonNull;


import com.example.du_an_1.model.CategoryCustomer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryCustomerDao {

    private static String TAG = CategoryCustomerDao.class.getSimpleName();
    private static ArrayList<CategoryCustomer> categoryCustomers = new ArrayList<>();

    //thêm một danh mục khách hàng trong db
    public static void insertCategoryCustomer(CategoryCustomer categoryCustomer, String idShopAccount) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child(idShopAccount)
                .child("CategoryCustomers")
                .child(categoryCustomer.getIdCategory())
                .setValue(categoryCustomer);
    }

    public static ArrayList<CategoryCustomer> getCategoryCustomers(String idShopAccount){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child(idShopAccount)
                .child("CategoryCustomers")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                CategoryCustomer categoryCustomer = dataSnapshot.getValue(CategoryCustomer.class);
                                Log.e(TAG,categoryCustomer.getIdCategory());
                            }
                        }else{
                            Log.e(TAG,"Ko có dữ liệu ở trong CategoryCustomers");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG,"Ko thể đọc dữ liệu: " + error.toString());
                    }
                });
        return categoryCustomers;
    }
}