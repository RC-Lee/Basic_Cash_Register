package com.example.assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Layouts
    NumberPicker quantityPicker;
    TextView quantity_tv, productType_tv, total_tv;
    ListView productList;
    Button buyBtn;
    // Apps
    Product currentProduct;
    ProductManager productManager;
    int productPosition;
    // alert dialog
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Alert dialog builder
        builder = new AlertDialog.Builder(this);

        // Find layouts
        quantityPicker = findViewById(R.id.quantity_picker);
        quantity_tv = findViewById(R.id.quantity_tv);
        productType_tv = findViewById(R.id.productType_tv);
        total_tv = findViewById(R.id.total_tv);
        buyBtn = findViewById(R.id.buy_btn);
        productList = findViewById(R.id.product_list_lv);

        // Get App values
        currentProduct = ((MainApp)getApplication()).mainProduct;
        productManager = ((MainApp)getApplication()).productManager;
        // Set initial list for products
        if(productManager.allProducts.isEmpty()){
            productManager.initialize();
        }

        // Set layout values
        quantity_tv.setText(String.valueOf(currentProduct.quantity));
        productType_tv.setText(currentProduct.name);
        total_tv.setText(currentProduct.getTotal());

        // Number Picker
        quantityPicker.setMinValue(0);
        quantityPicker.setMaxValue(100);
        quantityPicker.setValue(currentProduct.quantity);
        quantityPicker.setWrapSelectorWheel(true);
        quantityPicker.setOnValueChangedListener((numberPicker, oldVal, newVal) -> {
            currentProduct.quantity = newVal;
            quantity_tv.setText(String.valueOf(currentProduct.quantity));
            total_tv.setText(currentProduct.getTotal());
        });

        //List View
        ArrayList<Product> list = productManager.allProducts;
        ProductBaseAdapter productAdapter = new ProductBaseAdapter(list, this);
        productList.setAdapter(productAdapter);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selected = (Product) adapterView.getItemAtPosition(i);
                productPosition = i;
                currentProduct.price = selected.price;
                currentProduct.name = selected.name;
                productType_tv.setText(currentProduct.name);
                total_tv.setText(currentProduct.getTotal());
            }
        });

        //Button
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentProduct.quantity == 0 || currentProduct.name.equals("")){
                    Toast.makeText(MainActivity.this, "All fields required!", Toast.LENGTH_LONG).show();
                    Log.d("oncreate", currentProduct.name);
                }
                else{
                    Product productInList = (Product) productManager.allProducts.get(productPosition);
                    if(currentProduct.quantity > productInList.quantity){
                        Toast.makeText(MainActivity.this, "Not enough quantity!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        builder.setTitle("Thank you for your purchase!");
                        builder.setMessage(currentProduct.getPurchase());
                        builder.setCancelable(true);
                        builder.setNegativeButton("OK", null);
                        builder.show();

                        productManager.updateQuantity(productPosition, productInList.quantity - currentProduct.quantity);
                        ((MainApp)getApplication()).mainProduct = new Product();
                        currentProduct = ((MainApp)getApplication()).mainProduct;
                        quantity_tv.setText(String.valueOf(currentProduct.quantity));
                        productType_tv.setText(currentProduct.name);
                        total_tv.setText(currentProduct.getTotal());
                        quantityPicker.setValue(currentProduct.quantity);

                        productList.setAdapter(new ProductBaseAdapter(productManager.allProducts, MainActivity.this));

                    }
                }


            }
        });

    }

}