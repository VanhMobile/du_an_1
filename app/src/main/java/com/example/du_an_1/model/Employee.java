package com.example.du_an_1.model;

public class Employee {
    private String imgPath;
    private String name;
    private String idEmployee;
    private String numberPhone;
    private String address;
    private String password;
    private String idShop;

    private String note;

    public Employee(){

    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
