package com.example.assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {
    ArrayList<Product> productList;
    Context context;

    public ProductBaseAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_row_layout, null);

        TextView productText = view.findViewById(R.id.product_type_row);
        TextView quantityText = view.findViewById(R.id.quantity_row);
        TextView priceText = view.findViewById(R.id.price_row);

        productText.setText(productList.get(i).name);
        quantityText.setText(String.valueOf(productList.get(i).quantity));
        priceText.setText(String.valueOf(productList.get(i).price));
        return view;
    }
}
