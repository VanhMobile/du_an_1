package com.example.du_an_1.desgin_pattern.build_pantter;


import com.example.du_an_1.model.CategoryCustomer;

public class CategoryCustomerBuilder {
    private CategoryCustomer categoryCustomer = new CategoryCustomer();

    public CategoryCustomerBuilder addId(String id){
        categoryCustomer.setIdCategory(id);
        return this;
    }

    public CategoryCustomerBuilder addName(String name){
        categoryCustomer.setNameCategory(name);
        return this;
    }

    public CategoryCustomerBuilder addNote(String note){
        categoryCustomer.setNote(note);
        return this;
    }

    public CategoryCustomer build(){
        return categoryCustomer;
    }
}
