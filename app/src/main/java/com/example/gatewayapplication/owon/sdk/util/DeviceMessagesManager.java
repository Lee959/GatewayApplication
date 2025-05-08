package com.example.gatewayapplication.owon.sdk.util;

import com.example.gatewayapplication.DeviceModel;
import com.example.gatewayapplication.GatewayListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Device message manager class that handles communication with devices and gateways.
 * This class encapsulates methods for controlling and querying devices such as lights, motors, sensors, etc.
 */
public class DeviceMessagesManager {

    private static DeviceMessagesManager instance;
    private List<SocketMessageListener> listeners;


    private DeviceMessagesManager() {
        listeners = new ArrayList<>();
    }

    public static synchronized DeviceMessagesManager getInstance() {
        if (instance == null) {
            instance = new DeviceMessagesManager();
        }
        return instance;
    }

    public void registerMessageListener(SocketMessageListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unregisterMessageListener(SocketMessageListener listener) {
        listeners.remove(listener);
    }

    public void QueryGatewayList(int page, int pagesize) {
        // TODO: Send a request to the server
        simulateGatewayListResponse();
    }

    public void GetEpList() {
        // TODO: Send a request to the server
        simulateDeviceListResponse();
    }

    /**
     * Get the state of a device.
     *
     * @param device The device to query.
     * @param cache  Use cache flag (0 for no cache, 1 for using cache).
     */
    public void getDeviceState(DeviceModel device, int cache) {
        // TODO: Send a request to the server
        simulateDeviceStateResponse(device, cache);
    }


    private void simulateGatewayListResponse() {
        // Create a list of simulated gateways
        List<com.example.gatewayapplication.GatewayModel> gateways = new ArrayList<>();
        gateways.add(new com.example.gatewayapplication.GatewayModel("Living Room Gateway", "gw001"));
        gateways.add(new com.example.gatewayapplication.GatewayModel("Bedroom Gateway", "gw002"));
        gateways.add(new com.example.gatewayapplication.GatewayModel("Kitchen Gateway", "gw003"));

        // Create a gateway list bean
        GatewayListBean gatewayListBean = new GatewayListBean(gateways, gateways.size());

        // Notify listeners
        for (SocketMessageListener listener : listeners) {
            listener.getMessage(Constants.UpdateEPList, gatewayListBean);
        }
    }

    /**
     * Simulate a device list response for demonstration purposes.
     */
    private void simulateDeviceListResponse() {
        // Create a list of simulated devices
        List<DeviceModel> devices = new ArrayList<>();

        // Add different types of devices
        devices.add(new DeviceModel("Light A", "dev001", Constants.LIGHT_601, true));
        devices.add(new DeviceModel("Light B", "dev002", Constants.LIGHT_EXTEND_LO_COLOR_TEMP_GOODVB, true));
        devices.add(new DeviceModel("Temperature Sensor", "dev003", com.example.gatewayapplication.DeviceTypeCode.TH_SENSOR, true));
        devices.add(new DeviceModel("Motion Sensor", "dev004", com.example.gatewayapplication.DeviceTypeCode.MOTION_SENSOR_ZONE, false));
        devices.add(new DeviceModel("Smoke Detector", "dev005", com.example.gatewayapplication.DeviceTypeCode.SMOKE_SENSOR_ZONE, true));
        devices.add(new DeviceModel("Curtain Motor", "dev006", com.example.gatewayapplication.DeviceTypeCode.WARN_MOTOR, false));

        // Create a device list bean
        com.example.gatewayapplication.DeviceListBean deviceListBean = new com.example.gatewayapplication.DeviceListBean(devices);

        // Notify listeners
        for (SocketMessageListener listener : listeners) {
            listener.getMessage(Constants.ZigBeeGetEPList, deviceListBean);
        }
    }

    /**
     * Simulate a device state response for demonstration purposes.
     *
     * @param device The device to simulate a response for.
     */
    private void simulateDeviceStateResponse(DeviceModel device, int cache) {
        // For demonstration purposes, we'll simulate different responses based on device type
        int commandID;
        Object bean;

        switch (device.getDeviceType()) {
            case Constants.LIGHT_601:
            case Constants.LIGHT_EXTEND_LO_COLOR_TEMP_GOODVB:
                commandID = Constants.UpdateSwitchgear;
                bean = createLightStateBean(device);
                break;

            case com.example.gatewayapplication.DeviceTypeCode.TH_SENSOR:
                commandID = Constants.THI_UPDATE;
                bean = createTempHumidityStateBean(device);
                break;

            case com.example.gatewayapplication.DeviceTypeCode.LX_SENSOR:
                commandID = Constants.ILLUM_UPDATE;
                bean = createLightSensorStateBean(device);
                break;

            case com.example.gatewayapplication.DeviceTypeCode.MOTION_SENSOR_ZONE:
                commandID = Constants.MotionSensorUpdate;
                bean = createMotionSensorStateBean(device);
                break;

            case com.example.gatewayapplication.DeviceTypeCode.SMOKE_SENSOR_ZONE:
                commandID = Constants.WarningSensor;
                bean = createSmokeDetectorStateBean(device);
                break;

            default:
                commandID = 0;
                bean = null;
                break;
        }

        if (bean != null) {
            for (SocketMessageListener listener : listeners) {
                listener.getMessage(commandID, bean);
            }
        }
    }

    // Helper Function: Create device state beans for demonstration purposes

    private Object createLightStateBean(DeviceModel device) {
        return new Object();
    }

    private Object createTempHumidityStateBean(DeviceModel device) {
        return new Object();
    }

    private Object createLightSensorStateBean(DeviceModel device) {
        return new Object();
    }

    private Object createMotionSensorStateBean(DeviceModel device) {
        return new Object();
    }

    private Object createSmokeDetectorStateBean(DeviceModel device) {
        return new Object();
    }
}