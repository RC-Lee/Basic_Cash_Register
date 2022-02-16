package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity_Detail extends AppCompatActivity {

    TextView detailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailsText = findViewById(R.id.details_tv);

        Purchase selectedPurchase = getIntent().getExtras().getParcelable("selected");
        detailsText.setText(selectedPurchase.getDetails());
    }
}