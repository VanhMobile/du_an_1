package com.example.du_an_1.desgin_pattern.single_pantter;


import com.example.du_an_1.model.Bill;

public class BillSingle {
    private static BillSingle instance;

    private Bill bill = new Bill();

    public static BillSingle getInstance(){
        if (instance == null){
            instance = new BillSingle();
        }
        return instance;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
