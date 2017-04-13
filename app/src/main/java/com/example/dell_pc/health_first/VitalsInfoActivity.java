package com.example.dell_pc.health_first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ashish pc on 11-Apr-17.
 */

public class VitalsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals_layout);
        Button chol = (Button) findViewById(R.id.chol);
        Button pres = (Button) findViewById(R.id.pres);
        Button sugar = (Button) findViewById(R.id.sugar);
        chol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(VitalsInfoActivity.this, CholInfoActivity.class);
                startActivity(in);
            }
        });

        pres.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(VitalsInfoActivity.this, PresInfoActivity.class);
                startActivity(in);
            }
        });

        sugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(VitalsInfoActivity.this, SugarInfoActivity.class);
                startActivity(in);
            }
        });

    }
}
