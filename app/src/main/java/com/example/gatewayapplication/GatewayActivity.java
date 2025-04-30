package com.example.gatewayapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class GatewayActivity extends AppCompatActivity {

    private ListView gatewayListView;
    private GateawayAdapter gateawayAdapter = null;
    private List<Gateaway> gateawayData = null;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = GatewayActivity.this;
        bindViews();
        gateawayData = new LinkedList<Gateaway>();
        gateawayAdapter = new GateawayAdapter((LinkedList<Gateaway>) gateawayData,context);
        gatewayListView.setAdapter(gateawayAdapter);

    }

    private void bindViews(){
        gatewayListView = (ListView) findViewById(R.id.gatewayListView);
    }
}