package com.example.assignment_2;

import java.util.ArrayList;

public class PurchaseManager {
    ArrayList<Purchase> purchaseList = new ArrayList<>(0);

    public void addNewPurchase(Purchase p){
        purchaseList.add(p);
    }
}
