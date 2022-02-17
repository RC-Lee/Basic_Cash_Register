package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Activity_History extends AppCompatActivity {

    RecyclerView historyList;
    PurchaseManager manager;
    PurchaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        manager = ((MainApp)getApplication()).purchaseManager;

        historyList = findViewById(R.id.history_rv);
        adapter = new PurchaseRecyclerAdapter(manager.purchaseList, this);
        historyList.setAdapter(adapter);
        historyList.setLayoutManager(new LinearLayoutManager(this));
    }
}