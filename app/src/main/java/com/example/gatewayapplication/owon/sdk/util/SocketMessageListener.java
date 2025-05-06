package com.example.gatewayapplication.owon.sdk.util;

public interface SocketMessageListener {

    void getMessage(int commandID, Object bean);
}