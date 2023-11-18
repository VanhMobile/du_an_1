package com.example.du_an_1.desgin_pattern.single_pantter;

import com.example.du_an_1.model.Employee;

public class AccountSingle {
    private static AccountSingle instance;

    private Employee account = new Employee();

    public Employee getAccount() {
        return account;
    }

    public void setAccount(Employee account) {
        this.account = account;
    }

    public static AccountSingle getInstance() {
        if (instance == null){
            instance = new AccountSingle();
        }
        return instance;
    }


}
