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

import com.example.gatewayapplication.owon.sdk.util.DeviceMessagesManager;
import com.example.gatewayapplication.owon.sdk.util.Constants;
import com.example.gatewayapplication.owon.sdk.util.SocketMessageListener;

public class MainActivity extends AppCompatActivity implements SocketMessageListener {

    private ListView gatewayListView;
    private GatewayAdapter gatewayAdapter;
    private List<GatewayModel> gatewayList;
    private DeviceMessagesManager deviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gatewayListView = findViewById(R.id.gateway_listview);
        deviceManager = DeviceMessagesManager.getInstance();
        deviceManager.registerMessageListener(this);
        gatewayList = new ArrayList<>();
        gatewayAdapter = new GatewayAdapter(this, gatewayList);
        gatewayListView.setAdapter(gatewayAdapter);

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
        deviceManager.QueryGatewayList(1, 10);
    }

    @Override
    public void getMessage(int commandID, Object bean) {
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
        deviceManager.unregisterMessageListener(this);
    }
}