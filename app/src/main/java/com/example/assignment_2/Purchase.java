package com.example.assignment_2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Purchase implements Parcelable {
    String productType;
    int quantity;
    String total;
    String purchaseDate;

    public Purchase(String productType , int quantity, String total, String purchaseDate) {
        this.productType = productType;
        this.quantity = quantity;
        this.total = total;
        this.purchaseDate = purchaseDate;
    }

    public String getDetails(){
        return String.format(Locale.ENGLISH,"Product: %s%nQuantity: %d%nTotal: %s%nDate: %s",
                productType, quantity, total, purchaseDate );
    }

    protected Purchase(Parcel in) {
        productType = in.readString();
        quantity = in.readInt();
        total = in.readString();
        purchaseDate = in.readString();
    }

    public static final Creator<Purchase> CREATOR = new Creator<Purchase>() {
        @Override
        public Purchase createFromParcel(Parcel in) {
            return new Purchase(in);
        }

        @Override
        public Purchase[] newArray(int size) {
            return new Purchase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productType);
        parcel.writeInt(quantity);
        parcel.writeString(total);
        parcel.writeString(purchaseDate);
    }
}
