package com.example.dell_pc.health_first;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by DELL-PC on 23-03-2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state=intent.getExtras().getString("extra");
        Log.e("Ringstate:extra",state);



        assert state!= null;
        switch (state){
            case "alarm on":
                startId=1;
                break;

            case "alarm off":
                startId=0;
                break;

            default:
                startId=0;
                break;


        }

        if(!this.isRunning && startId==1){
            media_song=MediaPlayer.create(this,R.raw.tone);
            media_song.start();


            NotificationManager notify_manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            Intent intent_main_activity=new Intent(this.getApplicationContext(),Alarm.class);
            PendingIntent pending_intent_main_activity=PendingIntent.getActivity(this,0,intent_main_activity,0);

            Notification notification_popup= new Notification.Builder(this).
                    setContentTitle("Time to Take Medicine!").
                    setContentText("Medicine name: Crocin").
                    setContentIntent(pending_intent_main_activity).
                    setSmallIcon(R.drawable.notification_icon).
                    setAutoCancel(true).build();
            notify_manager.notify(0,notification_popup);
            this.isRunning=true;
            this.startId=0;
        }
        else if (this.isRunning && startId==0){
            media_song.stop();
            media_song.reset();
            this.isRunning=false;
            this.startId=0;
        }
        else if (!this.isRunning && startId==0){
            this.startId=0;
            this.isRunning=false;

        }
        else if (this.isRunning && startId==1){
            this.startId=1;
            this.isRunning=true;
        }
        else{

        }




        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.

        Toast.makeText(this, "On destroy called", Toast.LENGTH_SHORT).show();
    }




}
