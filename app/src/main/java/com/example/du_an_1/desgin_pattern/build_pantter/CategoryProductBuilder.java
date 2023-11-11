package com.example.du_an_1.desgin_pattern.build_pantter;


import com.example.du_an_1.model.CategoryProduct;

public class CategoryProductBuilder {
    private CategoryProduct categoryProduct = new CategoryProduct();

    public CategoryProductBuilder addId(String id){
        categoryProduct.setIdCategory(id);
        return this;
    }

    public CategoryProductBuilder addName(String name){
        categoryProduct.setNameCategory(name);
        return this;
    }

    public CategoryProductBuilder addNote(String note){
        categoryProduct.setNote(note);
        return this;
    }

    public CategoryProduct build(){
        return categoryProduct;
    }
}
