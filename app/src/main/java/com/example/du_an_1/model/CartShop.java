package com.example.du_an_1.model;

public class CartShop {
    private Product product;
    private int quantity;

    public CartShop(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartShop() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
