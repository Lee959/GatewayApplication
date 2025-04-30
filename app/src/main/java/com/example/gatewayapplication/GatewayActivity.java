package com.example.gatewayapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class GatewayActivity extends AppCompatActivity {

    private ListView gatewaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gatewaylist = findViewById(R.id.gatewayList);
    }
}