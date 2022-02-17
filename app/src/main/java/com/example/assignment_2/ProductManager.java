package com.example.assignment_2;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> allProducts = new ArrayList(0);

    public void addProduct(Product p){
        allProducts.add(p);
    }

    public void initialize(){
        addProduct(new Product("Pants", 10, 20.44));
        addProduct(new Product("Shoes", 100, 10.44));
        addProduct(new Product("Hats", 30, 5.90));
    }
}
