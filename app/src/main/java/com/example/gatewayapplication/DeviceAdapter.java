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
import androidx.core.content.ContextCompat;

import com.example.gatewayapplication.owon.sdk.util.Constants;

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
            viewHolder.deviceStatus.setText("在线");
            viewHolder.deviceStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
        } else {
            viewHolder.deviceStatus.setText("离线");
            viewHolder.deviceStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        }

        return convertView;
    }

    private void setDeviceIcon(ImageView imageView, int deviceType) {
        switch (deviceType) {
            case Constants.LIGHT_601:
            case Constants.LIGHT_EXTEND_LO_COLOR_TEMP_GOODVB:
                imageView.setImageResource(R.drawable.lightbulb_24px);
                break;
            case DeviceTypeCode.TH_SENSOR:
                imageView.setImageResource(R.drawable.thermostat_24px);
                break;
            case DeviceTypeCode.LX_SENSOR:
                imageView.setImageResource(R.drawable.light_sensor_24px);
                break;
            case DeviceTypeCode.SMOKE_SENSOR_ZONE:
                imageView.setImageResource(R.drawable.detector_smoke_24px);
                break;
            case DeviceTypeCode.MOTION_SENSOR_ZONE:
                imageView.setImageResource(R.drawable.motion_sensor_24px);
                break;
            case DeviceTypeCode.AC_SENSOR:
                imageView.setImageResource(R.drawable.ac_controller_24px);
                break;
            case DeviceTypeCode.WARN_SENSOR:
                imageView.setImageResource(R.drawable.light_sound_sensor_24px);
                break;
            case DeviceTypeCode.WARN_MOTOR:
                imageView.setImageResource(R.drawable.curtains_24px);
                break;
            case DeviceTypeCode.DOOR_SENSOR:
                imageView.setImageResource(R.drawable.sensor_door_24px);
                break;
            default:
                imageView.setImageResource(R.drawable.device_unknown_24px);
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