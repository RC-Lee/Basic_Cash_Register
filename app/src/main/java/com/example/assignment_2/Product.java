package com.example.assignment_2;

import java.util.Locale;

public class Product {
    String name;
    int quantity;
    double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(){
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
    }

    public String getTotal(){
        return String.format(Locale.ENGLISH,"%.2f", price * quantity);
    }

    public String getPurchase() {
        return "You have purchased " + quantity + " " + name + " for $" +getTotal();
    }

    public void updateQuantity(int quantity){
        this.quantity += quantity;
    }

}
