package com.example.gatewayapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;

public class GateawayAdapter extends BaseAdapter {
    private Context context;
    private LinkedList<Gateaway> gateaways;

    public GateawayAdapter(Context context, LinkedList<Gateaway> gateaways) {
        this.context = context;
        this.gateaways = gateaways;
    }


    @Override
    public int getCount() {
        return gateaways.size();
    }

    @Override
    public Object getItem(int position) {
        return gateaways.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
