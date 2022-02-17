package com.example.assignment_2;

import android.app.Application;

public class MainApp extends Application {
    Product mainProduct = new Product();
    Product shelfProduct = new Product();
    Product restockProduct = new Product();
    ProductManager productManager = new ProductManager();
    PurchaseManager purchaseManager = new PurchaseManager();
}
