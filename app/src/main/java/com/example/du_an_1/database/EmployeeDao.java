package com.example.du_an_1.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.du_an_1.model.Employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeDao {

    public static void insertEmployee(Employee employee) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        // tạo account cho nhân viên
        db.child("EmployeeAccount")
                .child(employee.getIdEmployee())
                .setValue(employee);

        db.child(employee.getIdShop())
                .child("Employees")
                .child(employee.getIdEmployee())
                .setValue(employee);
    }

    private static String TAG = EmployeeDao.class.getSimpleName();

    public static void getEmployees(GetData data){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        ArrayList<Employee> employees = new ArrayList<>();
        db.child("EmployeeAccount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Employee employee = dataSnapshot.getValue(Employee.class);
                        employees.add(employee);
                        Log.e(TAG,employee.getIdEmployee());
                    }
                }else{
                    Log.e(TAG,"không có dữ liệu trong Employees");
                }
                data.getData(employees);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG,"ko thể đọc dữ liệu db: " + error);
            }
        });
    }

    public static void getEmployees(String idShopAccount,GetData data){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        ArrayList<Employee> employees = new ArrayList<>();
        db.child(idShopAccount).child("Employees").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Employee employee = dataSnapshot.getValue(Employee.class);
                        employees.add(employee);
                        Log.e(TAG,employee.getIdEmployee());
                    }
                }else{
                    Log.e(TAG,"không có dữ liệu trong Employees");
                }
                data.getData(employees);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG,"ko thể đọc dữ liệu db: " + error);
            }
        });
    }

    public interface GetData{
        void getData(ArrayList<Employee> employees);
    }
}
