package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.MainActivity;
import com.example.du_an_1.adapter.DetailBillAdapter;
import com.example.du_an_1.database.EmployeeDao;
import com.example.du_an_1.databinding.FragmentDetailBillBinding;
import com.example.du_an_1.desgin_pattern.single_pantter.BillSingle;
import com.example.du_an_1.funtions.MoneyFormat;
import com.example.du_an_1.model.Bill;
import com.example.du_an_1.model.Employee;

import java.util.ArrayList;
import java.util.EnumMap;


public class DetailBillFragment extends Fragment {

    private FragmentDetailBillBinding detailBillBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detailBillBinding = FragmentDetailBillBinding.inflate(inflater,container,false);
        initView();
        return detailBillBinding.getRoot();
    }

    private void initView() {
        Bill bill = BillSingle.getInstance().getBill();

        if (bill == null){
            return;
        }
        detailBillBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            }
        });
        int cost = 0;
        for (int i = 0; i < bill.getListProduct().size(); i++){
            cost += (bill.getListProduct().get(i).getProduct().getCost() * bill.getListProduct().get(i).getQuantity());
        }
        detailBillBinding.BillId.setText("Mã hóa đơn: " + bill.getBillId());
        detailBillBinding.date.setText(bill.getDate());
        detailBillBinding.quantity.setText(bill.getQuantity()+"");
        detailBillBinding.cost.setText(MoneyFormat.moneyFormat(cost));
        detailBillBinding.total.setText(MoneyFormat.moneyFormat(bill.getSumPrice()));
        detailBillBinding.numberPhoneCustomer.setText(bill.getCustomer().getNumberPhone());
        detailBillBinding.addressCustomer.setText(bill.getCustomer().getAddress());
        detailBillBinding.CustomerType.setText(bill.getCustomer().getCustomerType());
        detailBillBinding.payMethod.setText(bill.getPayMethod());
        detailBillBinding.sumPrice.setText(MoneyFormat.moneyFormat(bill.getSumPrice()));
        detailBillBinding.typeBill.setText(bill.getBillType());
        DetailBillAdapter billAdapter  = new DetailBillAdapter(bill.getListProduct(), bill.getBillType());
        detailBillBinding.recycBill.setAdapter(billAdapter);
        EmployeeDao.getEmployees(new EmployeeDao.GetData() {
            @Override
            public void getData(ArrayList<Employee> employees) {
                for (Employee employee : employees){
                    if (employee.getIdEmployee().equals(bill.getIdAccount())){
                        detailBillBinding.IdAccount.setText(employee.getName()+"_"+employee.getNumberPhone());
                    }
                }
            }
        });
        detailBillBinding.recycBill.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }
}