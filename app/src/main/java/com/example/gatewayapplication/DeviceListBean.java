package com.example.gatewayapplication;

import java.util.List;

public class DeviceListBean {
    private List<DeviceModel> devices;

    public DeviceListBean(List<DeviceModel> devices) {
        this.devices = devices;
    }

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }
}