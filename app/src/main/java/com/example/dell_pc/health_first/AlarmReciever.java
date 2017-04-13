package com.example.dell_pc.health_first;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by DELL-PC on 21-03-2017.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in reciever","Yay");

        String get_your_string=intent.getExtras().getString("extra");
        Log.e("What is the key?",get_your_string);
        Intent service_intent=new Intent(context,RingtonePlayingService.class);
        service_intent.putExtra("extra",get_your_string);
        context.startService(service_intent);
    }
}
