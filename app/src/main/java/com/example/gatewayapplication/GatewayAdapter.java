package com.example.gatewayapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GatewayAdapter extends ArrayAdapter<GatewayModel> {

    private Context context;
    private List<GatewayModel> gatewayList;

    public GatewayAdapter(Context context, List<GatewayModel> gatewayList) {
        super(context, R.layout.item_gateway, gatewayList);
        this.context = context;
        this.gatewayList = gatewayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gateway, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.gatewayIcon = convertView.findViewById(R.id.gateway_icon);
            viewHolder.gatewayName = convertView.findViewById(R.id.gateway_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GatewayModel gateway = gatewayList.get(position);

        viewHolder.gatewayIcon.setImageResource(R.drawable.ic_gateway);
        viewHolder.gatewayName.setText(gateway.getName());

        return convertView;
    }

    private static class ViewHolder {
        ImageView gatewayIcon;
        TextView gatewayName;
    }
}