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
                    data.getData(employees);
                }else{
                    Log.e(TAG,"không có dữ liệu trong Employees");
                }
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
