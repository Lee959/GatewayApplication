package com.example.gatewayapplication;

import java.util.List;

public class GatewayListBean {
    private List<GatewayModel> gateways;
    private int totalCount;

    public GatewayListBean(List<GatewayModel> gateways, int totalCount) {
        this.gateways = gateways;
        this.totalCount = totalCount;
    }

    public List<GatewayModel> getGateways() {
        return gateways;
    }
    public void setGateways(List<GatewayModel> gateways) {
        this.gateways = gateways;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}