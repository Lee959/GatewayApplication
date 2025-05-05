package com.example.gatewayapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import owon.sdk.util.DeviceMessagesManager;
import owon.sdk.util.SocketMessageListener;
import owon.sdk.util.Constants;

public class DeviceListActivity extends AppCompatActivity implements SocketMessageListener {

    private TextView gatewayNameTextView;
    private ListView deviceListView;
    private DeviceAdapter deviceAdapter;
    private List<DeviceModel> deviceList;
    private DeviceMessagesManager deviceManager;
    private String gatewayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        // Get gateway name from intent
        gatewayName = getIntent().getStringExtra("GATEWAY_NAME");

        // Initialize views
        gatewayNameTextView = findViewById(R.id.gateway_name_textview);
        deviceListView = findViewById(R.id.device_listview);

        // Set gateway name
        gatewayNameTextView.setText("Gateway: " + gatewayName);

        // Initialize device manager
        deviceManager = DeviceMessagesManager.getInstance();
        deviceManager.registerMessageListener(this);

        // Initialize device list
        deviceList = new ArrayList<>();
        deviceAdapter = new DeviceAdapter(this, deviceList);
        deviceListView.setAdapter(deviceAdapter);

        // Query device list
        queryDeviceList();
    }

    private void queryDeviceList() {
        // Query device list using DeviceMessagesManager
        deviceManager.GetEpList();
    }

    @Override
    public void getMessage(int commandID, Object bean) {
        // Handle message callbacks
        if (commandID == Constants.ZigBeeGetEPList) {
            // Device list callback
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DeviceListBean deviceListBean = (DeviceListBean) bean;
                    if (deviceListBean != null && deviceListBean.getDevices() != null) {
                        deviceList.clear();
                        deviceList.addAll(deviceListBean.getDevices());
                        deviceAdapter.notifyDataSetChanged();

                        // Query device states for all devices
                        for (DeviceModel device : deviceList) {
                            queryDeviceState(device);
                        }
                    } else {
                        Toast.makeText(DeviceListActivity.this, "No devices found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (commandID == Constants.UpdateSwitchgear ||
                commandID == Constants.UpdateLight ||
                commandID == Constants.THI_UPDATE ||
                commandID == Constants.ILLUM_UPDATE ||
                commandID == Constants.MotionSensorUpdate ||
                commandID == Constants.MotionSensor ||
                commandID == Constants.WarningSensor) {
            // Device state callback
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Update device state in list
                    updateDeviceState(commandID, bean);
                }
            });
        }
    }

    private void queryDeviceState(DeviceModel device) {
        // Query device state using DeviceMessagesManager
        deviceManager.getDeviceState(device, 0); // Use 0 to not use cache
    }

    private void updateDeviceState(int commandID, Object bean) {
        // Update device state based on command ID and bean type
        // This would require more implementation based on device types
        deviceAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister message listener
        deviceManager.unregisterMessageListener(this);
    }
}