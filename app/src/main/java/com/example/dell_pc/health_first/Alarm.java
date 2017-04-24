package com.example.dell_pc.health_first;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by ashish pc on 28-Mar-17.
 */

public class Alarm extends AppCompatActivity {
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    TextView update;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm);

//Extract the data…
        this.context = this;
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        update=(TextView) findViewById(R.id.updatemessage);

        final Calendar calendar = Calendar.getInstance();

        Button start_alarm = (Button) findViewById(R.id.set_alarm);
        Button button10 =(Button)findViewById(R.id.button10);
        Button end_alarm = (Button) findViewById(R.id.end_alarm);
        final Intent my_intent=new Intent(this.context,AlarmReciever.class);
        TextView med=new TextView(getApplicationContext());
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());

                int hour=alarm_timepicker.getHour();
                int minute=alarm_timepicker.getMinute();
                String hour_string=String.valueOf(hour);
                String minute_string=String.valueOf(minute);


                my_intent.putExtra("extra","alarm on");
                pending_intent=PendingIntent.getBroadcast(context,0,my_intent,0);
                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);
                set_alarm_text("Alarm On !!"+hour_string+":"+minute_string);




            }
        });

        end_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarm_manager.cancel(pending_intent);
                //pending_intent.cancel();

                my_intent.putExtra("extra","alarm off");
                sendBroadcast(my_intent);
                end_alarm_text("Alarm Off !!");

            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Alarm.this, Reminder1.class);
                startActivity(in);
            }
        });


    }

    public void set_alarm_text(String s) {
        update.setText(s);
    }

    public void end_alarm_text(String s) {
        update.setText(s);
    }

}