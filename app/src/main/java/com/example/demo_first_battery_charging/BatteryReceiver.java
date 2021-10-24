package com.example.demo_first_battery_charging;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {
    TextView state;
    TextView percentage;
    ImageView batteryImg;

    @SuppressLint("SetTextI18n")
    @Override
    public void onReceive(Context context, Intent batteryStatus) {
        state = ((MainActivity)context).findViewById(R.id.tv_charging);
        percentage = ((MainActivity)context).findViewById(R.id.tv_percentage);
        batteryImg = ((MainActivity)context).findViewById(R.id.iv_battery);


        String action = batteryStatus.getAction();
        // is it charging?
        if(action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)){
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String msg = "";


            //Status
            switch (status){
                case BatteryManager.BATTERY_STATUS_FULL:
                    msg = "Full";
                    break;

                case BatteryManager.BATTERY_STATUS_CHARGING:
                    msg = "Charging";
                    break;

                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    msg = "Not charging";
                    break;

                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    msg = "Unknown";
                    break;
            }

            state.setText(msg);


            //Percentage
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryPct = level * 100 / scale;
            percentage.setText((batteryPct) + "%");


            // Img
            Resources res = context.getResources();
            if(batteryPct >= 90){
                batteryImg.setImageResource(R.drawable.b100);
            } else if(batteryPct >= 65){
                batteryImg.setImageResource(R.drawable.b75);
            } else if(batteryPct >= 40){
                batteryImg.setImageResource(R.drawable.b50);
            } else if(batteryPct >= 15){
                batteryImg.setImageResource(R.drawable.b25);
            } else{
                batteryImg.setImageResource(R.drawable.b0);
            }
        }

    }
}
