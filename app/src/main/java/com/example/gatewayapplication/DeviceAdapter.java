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

public class DeviceAdapter extends ArrayAdapter<DeviceModel> {

    private Context context;
    private List<DeviceModel> deviceList;

    public DeviceAdapter(Context context, List<DeviceModel> deviceList) {
        super(context, R.layout.item_device, deviceList);
        this.context = context;
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.deviceIcon = convertView.findViewById(R.id.device_icon);
            viewHolder.deviceName = convertView.findViewById(R.id.device_name);
            viewHolder.deviceType = convertView.findViewById(R.id.device_type);
            viewHolder.deviceStatus = convertView.findViewById(R.id.device_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DeviceModel device = deviceList.get(position);
        setDeviceIcon(viewHolder.deviceIcon, device.getDeviceType());

        viewHolder.deviceName.setText(device.getName());
        viewHolder.deviceType.setText(device.getDeviceTypeName());

        if (device.isLinkStatus()) {
            viewHolder.deviceStatus.setText("Online");
            viewHolder.deviceStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            viewHolder.deviceStatus.setText("Offline");
            viewHolder.deviceStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }

        return convertView;
    }

    private void setDeviceIcon(ImageView imageView, int deviceType) {
        switch (deviceType) {
            case Constants.LIGHT_601:
            case Constants.LIGHT_EXTEND_LO_COLOR_TEMP_GOODVB:
                imageView.setImageResource(R.drawable.ic_light);
                break;
            case DeviceTypeCode.TH_SENSOR:
                imageView.setImageResource(R.drawable.ic_temp_sensor);
                break;
            case DeviceTypeCode.LX_SENSOR:
                imageView.setImageResource(R.drawable.ic_light_sensor);
                break;
            case DeviceTypeCode.SMOKE_SENSOR_ZONE:
                imageView.setImageResource(R.drawable.ic_smoke_sensor);
                break;
            case DeviceTypeCode.MOTION_SENSOR_ZONE:
                imageView.setImageResource(R.drawable.ic_motion_sensor);
                break;
            case DeviceTypeCode.AC_SENSOR:
                imageView.setImageResource(R.drawable.ic_ir_controller);
                break;
            case DeviceTypeCode.WARN_SENSOR:
                imageView.setImageResource(R.drawable.ic_alarm);
                break;
            case DeviceTypeCode.WARN_MOTOR:
                imageView.setImageResource(R.drawable.ic_curtain);
                break;
            case DeviceTypeCode.DOOR_SENSOR:
                imageView.setImageResource(R.drawable.ic_door_sensor);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_device);
                break;
        }
    }

    private static class ViewHolder {
        ImageView deviceIcon;
        TextView deviceName;
        TextView deviceType;
        TextView deviceStatus;
    }
}