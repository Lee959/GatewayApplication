package com.example.gatewayapplication;

import java.util.ArrayList;
import java.util.LinkedList;

public class Gateaway {
    private String name;                            //Assume name is unique
    private ArrayList<Device> connected_Devive;

    // Constructor
    public Gateaway(String name) {
        this.name = name;
    }

    // getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Device> getConnected_Devive () {
        return this.connected_Devive;
    }

    public void getConnectedDevice() {

    }

}
