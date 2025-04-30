package com.example.gatewayapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
//        context = GatewayActivity.this;
        bindViews();
        gateawayData = new LinkedList<Gateaway>();
        gateawayAdapter = new GateawayAdapter((LinkedList<Gateaway>) gateawayData, context);
        gatewayListView.setAdapter(gateawayAdapter);


        gatewayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    private void bindViews() {
        gatewayListView = (ListView) findViewById(R.id.gatewayListView);
    }

    private void replaceFragment(Fragment fragment) {

        // Get fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace fragment and commit
        fragmentTransaction.replace(R.id.connectedDevice, fragment);
        fragmentTransaction.commit();
    }
}