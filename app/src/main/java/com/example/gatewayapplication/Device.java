package com.example.gatewayapplication;

public class Device {
    private int deviceType;         //设备类型码
    private String name;            //设备名称
    private boolean linkStatus;     //是否在线

    // Constructor
    public Device (int deviceType, String name, boolean linkStatus) {
        this.deviceType = deviceType;
        this.name = name;
        this.linkStatus = linkStatus;
    }
}


