package com.example.dell_pc.health_first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ashish pc on 25-Mar-17.
 */

public class Monitoring extends AppCompatActivity{
    Button button5;
    //Button button4;
    Button button6;
    Button button7;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);
        button5 = (Button) findViewById(R.id.button5);
       // button4 = (Button) findViewById(R.id.button4);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Monitoring.this, Reminder1.class);
                startActivity(in);

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Monitoring.this, notepad.class);
                startActivity(in);

            }
        });


    }
}
