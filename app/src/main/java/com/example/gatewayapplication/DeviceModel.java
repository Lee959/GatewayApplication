package com.example.gatewayapplication;

public class DeviceModel {
    private String name;
    private String id;
    private int deviceType;
    private boolean linkStatus;

    public DeviceModel(String name, String id, int deviceType, boolean linkStatus) {
        this.name = name;
        this.id = id;
        this.deviceType = deviceType;
        this.linkStatus = linkStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(boolean linkStatus) {
        this.linkStatus = linkStatus;
    }

    public String getDeviceTypeName() {
        switch (deviceType) {
            case Constants.LIGHT_601:
                return "Basic Light";
            case Constants.LIGHT_EXTEND_LO_COLOR_TEMP_GOODVB:
                return "Smart Light";
            case DeviceTypeCode.TH_SENSOR:
                return "Temperature & Humidity Sensor";
            case DeviceTypeCode.LX_SENSOR:
                return "Light Sensor";
            case DeviceTypeCode.SMOKE_SENSOR_ZONE:
                return "Smoke Detector";
            case DeviceTypeCode.MOTION_SENSOR_ZONE:
                return "Motion Sensor";
            case DeviceTypeCode.AC_SENSOR:
                return "IR Controller";
            case DeviceTypeCode.WARN_SENSOR:
                return "Alarm";
            case DeviceTypeCode.WARN_MOTOR:
                return "Curtain Motor";
            case DeviceTypeCode.DOOR_SENSOR:
                return "Door Sensor";
            default:
                return "Unknown Device";
        }
    }
}