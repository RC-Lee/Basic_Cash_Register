package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Manager extends AppCompatActivity implements View.OnClickListener {
    Button historyBtn;
    Button restockBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        historyBtn = findViewById(R.id.history_btn);
        restockBtn = findViewById(R.id.restock_btn);

        historyBtn.setOnClickListener(this);
        restockBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.history_btn){
            Intent historyIntent = new Intent(this, Activity_History.class);
            startActivity(historyIntent);
        }
        if( id == R.id.restock_btn){
            Intent restockIntent = new Intent (this, Activity_Restock.class);
            startActivity(restockIntent);
        }
    }
}