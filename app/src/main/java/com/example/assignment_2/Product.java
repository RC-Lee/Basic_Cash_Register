package com.example.assignment_2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Product implements Parcelable {
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

    protected Product(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(quantity);
        parcel.writeDouble(price);
    }
}
