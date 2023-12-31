package com.example.du_an_1.desgin_pattern.build_pantter;


import com.example.du_an_1.model.Employee;

public class EmployeeBuilder {
    private Employee employee = new Employee();

    public EmployeeBuilder addImgPath(String imgPath){
        employee.setImgPath(imgPath);
        return this;
    }

    public EmployeeBuilder addId(String id){
        employee.setIdEmployee(id);
        return this;
    }

    public  EmployeeBuilder addName(String name){
        employee.setName(name);
        return this;
    }

    public EmployeeBuilder addNumberPhone(String numberPhone){
        employee.setNumberPhone(numberPhone);
        return this;
    }

    public EmployeeBuilder addAddress(String address){
        employee.setAddress(address);
        return this;
    }

    public EmployeeBuilder addPassword(String password){
        employee.setPassword(password);
        return this;
    }

    public EmployeeBuilder addIdShop(String id){
        employee.setIdShop(id);
        return this;
    }

    public EmployeeBuilder addNote(String note){
        employee.setNote(note);
        return this;
    }

    public Employee build(){
        return employee;
    }
}
