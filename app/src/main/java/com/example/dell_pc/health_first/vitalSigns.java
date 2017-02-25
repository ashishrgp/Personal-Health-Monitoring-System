package com.example.dell_pc.health_first;

import android.app.Activity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class vitalSigns extends Activity {
    private EditText glucose,cholesterol,heartRate,blood,body;
    private Button submit,back;
    ProgressDialog progressDialog;
    int myNum ;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs);

        //Pull data from firebase database
        //get all the fileds
        glucose = (EditText)findViewById(R.id.GlucoseID);
        cholesterol= (EditText)findViewById(R.id.CholesterolID);
        heartRate = (EditText)findViewById(R.id.HeartID);
        body= (EditText)findViewById(R.id.TempID);
        blood = (EditText)findViewById(R.id.BloodID);

        //get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();//get the current user UID
            //get refrence to database
            databaseReference = FirebaseDatabase.getInstance().getReference().child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //String height_ = (String)dataSnapshot.child("height").getValue();
                    String Glucose = (String) dataSnapshot.child("glucose").getValue();
                    String Cholesterol = (String) dataSnapshot.child("cholesterol").getValue();
                    String HeartRate = (String)dataSnapshot.child("heartRate").getValue();
                    String Blood = (String)dataSnapshot.child("bloodpressure").getValue();
                    String Body = (String) dataSnapshot.child("bodyTemp").getValue();
                    if (Glucose != null){glucose.setText(Glucose.toString());}
                    if (Cholesterol != null){cholesterol.setText(Cholesterol.toString());}
                    if (HeartRate != null){heartRate.setText(HeartRate.toString());}
                    if (Cholesterol != null){cholesterol.setText(Cholesterol.toString());}
                    if (Blood != null){blood.setText(Blood.toString());}
                    if (Body != null){body.setText(Body.toString());}

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }

            });
        }
        //end pulling data from database
        submit =(Button)findViewById(R.id.Submit);
        back =(Button)findViewById(R.id.Back);

        progressDialog = new ProgressDialog(this);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                submitVital();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(vitalSigns.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

    private void submitVital(){

        String Glucose = glucose.getText().toString().trim();
        String Cholesterol = cholesterol.getText().toString().trim();
        String HeartRate = heartRate.getText().toString().trim();
        String Body = body.getText().toString().trim();
        String Blood = blood.getText().toString().trim();

        databaseReference = FirebaseDatabase.getInstance().getReference();

         if(TextUtils.isEmpty(Glucose) && TextUtils.isEmpty(Cholesterol) && TextUtils.isEmpty(HeartRate) &&
         TextUtils.isEmpty(Body)&& TextUtils.isEmpty(Blood)){
            Toast.makeText(this," Please Fill The Fields Completely ",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Body.length() != 0 ){
            myNum = Integer.parseInt(Body);
            if (myNum >= 102 && myNum < 110 ){
                Toast.makeText(this," Please Contact With Your Doctor",Toast.LENGTH_SHORT).show();
                return;
            }
            if(myNum < 90 || myNum > 110){
                Toast.makeText(this," Please Enter a valid Temperature",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            Map<String,Object> taskMap = new HashMap<String,Object>();

            taskMap.put("glucose", Glucose);
            taskMap.put("cholesterol",Cholesterol);
            taskMap.put("heartRate",HeartRate);
            taskMap.put("bloodpressure",Blood);
            taskMap.put("bodyTemp",Body);


            if (user != null) {
                databaseReference.child(uid).updateChildren(taskMap);
                //displaying a success toast
                Toast.makeText(this," VitalSigns Updated Successfully",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
