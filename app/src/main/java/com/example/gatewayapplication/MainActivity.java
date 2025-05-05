package com.example.gatewayapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import owon.sdk.util.DeviceMessagesManager;
import owon.sdk.util.SocketMessageListener;
import owon.sdk.util.Constants;

public class MainActivity extends AppCompatActivity implements SocketMessageListener {

    private ListView gatewayListView;
    private GatewayAdapter gatewayAdapter;
    private List<GatewayModel> gatewayList;
    private DeviceMessagesManager deviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        gatewayListView = findViewById(R.id.gateway_listview);

        // Initialize device manager
        deviceManager = DeviceMessagesManager.getInstance();
        deviceManager.registerMessageListener(this);

        // Initialize gateway list
        gatewayList = new ArrayList<>();
        gatewayAdapter = new GatewayAdapter(this, gatewayList);
        gatewayListView.setAdapter(gatewayAdapter);

        // Set item click listener
        gatewayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GatewayModel gateway = gatewayList.get(position);
                // Launch DeviceListActivity with the selected gateway
                Intent intent = new Intent(MainActivity.this, DeviceListActivity.class);
                intent.putExtra("GATEWAY_NAME", gateway.getName());
                startActivity(intent);
            }
        });

        // Query gateway list
        queryGatewayList();
    }

    private void queryGatewayList() {
        // Query gateway list using DeviceMessagesManager
        deviceManager.QueryGatewayList(1, 10); // page 1, 10 items per page
    }

    @Override
    public void getMessage(int commandID, Object bean) {
        // Handle message callbacks
        if (commandID == Constants.UpdateEPList) {
            // Gateway list callback
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GatewayListBean gatewayListBean = (GatewayListBean) bean;
                    if (gatewayListBean != null && gatewayListBean.getGateways() != null) {
                        gatewayList.clear();
                        gatewayList.addAll(gatewayListBean.getGateways());
                        gatewayAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "No gateways found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister message listener
        deviceManager.unregisterMessageListener(this);
    }
}