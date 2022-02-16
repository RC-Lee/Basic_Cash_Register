package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Restock extends AppCompatActivity implements View.OnClickListener {
    TextView productText;
    EditText newQuantity;
    Button okBtn;
    Button cancelBtn;
    ListView restockList;

    ProductManager manager;
    ProductBaseAdapter adapter;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        productText =  findViewById(R.id.restock_product);
        newQuantity = findViewById(R.id.new_quantity_et);
        okBtn = findViewById(R.id.ok_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        restockList = findViewById(R.id.restock_product_list);

        manager = ((MainApp)getApplication()).productManager;
        ArrayList<Product> list = manager.allProducts;
        adapter = new ProductBaseAdapter(list, this);
        restockList.setAdapter(adapter);

        restockList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selectedProduct = (Product) adapterView.getItemAtPosition(i);
                productText.setText(selectedProduct.name);
                position = i;
            }
        });

        cancelBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.cancel_btn){
            Intent managerIntent = new Intent(this, Activity_Manager.class);
            startActivity(managerIntent);
        }
        if(id == R.id.ok_btn){
            if(newQuantity.getText().toString().isEmpty() || productText.getText().toString().isEmpty()){
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_LONG).show();
            }
            else{
                manager.updateQuantity(position, Integer.parseInt(newQuantity.getText().toString()));
                adapter.notifyDataSetChanged();

                productText.setText("");
                newQuantity.setText("");
            }
        }
    }
}