package com.example.dell_pc.health_first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateProfileActivity extends Activity  {
    Button b1,b2,b3;
    EditText ed1;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateProfileActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(UpdateProfileActivity.this,HomeActivity.class);
                startActivity(in);



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saving Data...",Toast.LENGTH_SHORT).show();
                // Save all values in the database
                //password validation
                //Copy code from reset password


            }
        });

    }
}