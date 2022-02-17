package com.example.assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    // Layouts
    NumberPicker quantityPicker;
    TextView quantity_tv, productType_tv, total_tv;
    ListView productList;
    Button buyBtn;

    // Apps
    Product currentPurchase; //Stores the current product(s) that is being purchased
    Product shelfProduct; //Stores the stock details of the product being purchased
    ProductManager productManager;
    ProductBaseAdapter productAdapter;
    PurchaseManager purchaseManager;
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
        currentPurchase = ((MainApp)getApplication()).mainProduct;
        shelfProduct = ((MainApp)getApplication()).shelfProduct;
        productManager = ((MainApp)getApplication()).productManager;
        purchaseManager = ((MainApp)getApplication()).purchaseManager;

        // Set initial list for products
        if(productManager.allProducts.isEmpty()){
            productManager.initialize();
        }

        // Set layout values
        this.setViewValues();

        // Number Picker
        quantityPicker.setMinValue(0);
        quantityPicker.setMaxValue(100);
        quantityPicker.setValue(currentPurchase.quantity);
        quantityPicker.setWrapSelectorWheel(true);
        quantityPicker.setOnValueChangedListener((numberPicker, oldVal, newVal) -> {
            currentPurchase.quantity = newVal;
            this.setViewValues();
        });

        //List View
        ArrayList<Product> list = productManager.allProducts;
        productAdapter = new ProductBaseAdapter(list, this);
        productList.setAdapter(productAdapter);
        productList.setOnItemClickListener((adapterView, view, i, l) -> {
            ((MainApp)getApplication()).shelfProduct = (Product) adapterView.getItemAtPosition(i);
            shelfProduct = ((MainApp)getApplication()).shelfProduct;
            currentPurchase.price = shelfProduct.price;
            currentPurchase.name = shelfProduct.name;
            this.setViewValues();
        });
    }

    public void makePurchase(View view){
        if(currentPurchase.quantity == 0 || currentPurchase.name.equals("")){
            Toast.makeText(MainActivity.this, "All fields required!", Toast.LENGTH_LONG).show();
        }
        else{
            if(currentPurchase.quantity > shelfProduct.quantity){
                Toast.makeText(MainActivity.this, "Not enough quantity!", Toast.LENGTH_LONG).show();
            }
            else{
                // Build the alert dialogue
                builder.setTitle("Thank you for your purchase!");
                builder.setMessage(currentPurchase.getPurchase());
                builder.setCancelable(true);
                builder.setNegativeButton("OK", null);
                builder.show();

                // Add the new purchase into history
                Date date = new Date();
                Purchase purchase = new Purchase(currentPurchase.name, currentPurchase.quantity,
                        currentPurchase.getTotal(), date.toString());
                purchaseManager.addNewPurchase(purchase);

                // Update stock quantity
                shelfProduct.updateQuantity(-currentPurchase.quantity);
                productAdapter.notifyDataSetChanged();

                // Reset current product purchase and values
                ((MainApp)getApplication()).mainProduct = new Product();
                ((MainApp)getApplication()).shelfProduct = new Product();

                currentPurchase = ((MainApp)getApplication()).mainProduct;
                shelfProduct = ((MainApp)getApplication()).shelfProduct;
                this.setViewValues();
                quantityPicker.setValue(currentPurchase.quantity);
            }
        }
    }

    // manage function when the manager button is pressed
    // Starts a new activity on the Activity_Manager class
    public void manage(View view){
        Intent managerIntent = new Intent(this, Activity_Manager.class);
        startActivity(managerIntent);
    }

    // Set the view values based on the current product
    public void setViewValues(){
        quantity_tv.setText(String.valueOf(currentPurchase.quantity));
        productType_tv.setText(currentPurchase.name);
        total_tv.setText(currentPurchase.getTotal());
    }

}