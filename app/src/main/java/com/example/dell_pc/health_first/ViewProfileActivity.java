package com.example.dell_pc.health_first;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
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

public class ViewProfileActivity extends Activity {
    private EditText firstname,lastname,age,weight,hight,blood,doctor,EmerName,EmerContact,phonenumber;
    private Button submit,back;
    private String geneder;
    ProgressDialog progressDialog;
    int count,myNum ;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileview);
        //Pull data from firebase database
        //get all the fileds
        firstname = (EditText)findViewById(R.id.FirstName);
        lastname= (EditText)findViewById(R.id.LastName);
        age = (EditText)findViewById(R.id.AgeID);
        weight= (EditText)findViewById(R.id.WeightID);
        hight= (EditText)findViewById(R.id.HightID);
        blood = (EditText)findViewById(R.id.BloodID);
        doctor= (EditText)findViewById(R.id.doctorNameID);
        EmerName= (EditText)findViewById(R.id.emergencyNameID);
        EmerContact= (EditText)findViewById(R.id.emergencyNumberID);
        phonenumber=(EditText)findViewById(R.id.PhoneNumber);
        //get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();//get the current user UID
            //get refrence to database
            databaseReference = FirebaseDatabase.getInstance().getReference().child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String firstname_ = (String)dataSnapshot.child("firstName").getValue();
                    String lastname_ = (String)dataSnapshot.child("lastName").getValue();
                    Long phoneno_ = (Long)dataSnapshot.child("phoneNumber").getValue();
                    String age_ = (String) dataSnapshot.child("age").getValue();
                    String weight_ = (String)dataSnapshot.child("weight").getValue();
                    String height_ = (String)dataSnapshot.child("height").getValue();
                    String blood_ = (String)dataSnapshot.child("blood").getValue();
                    String docname_ = (String)dataSnapshot.child("doctor").getValue();
                    String emeconname = (String)dataSnapshot.child("emerName").getValue();
                    String emeconno = (String)dataSnapshot.child("emerContact").getValue();

                    if (firstname_ != null){firstname.setText(firstname_.toString());}
                    if (lastname_ != null){lastname.setText(lastname_.toString());}
                    if (phoneno_.toString().length()!=0)phonenumber.setText(phoneno_.toString());
                    if (age_ != null){age.setText(age_.toString());}
                    if (weight_ != null){weight.setText(weight_.toString());}
                    if (height_ != null){hight.setText(height_.toString());}
                    if(blood_ != null){blood.setText(blood_.toString());}
                    if (docname_ != null){doctor.setText(docname_.toString());}
                    if (emeconname != null){EmerName.setText(emeconname.toString());}
                    if(emeconno != null){EmerContact.setText(emeconno.toString());}

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

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[] {"Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dynamicSpinner.setAdapter(adapter);
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                geneder = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent in = new Intent(ViewProfileActivity.this,HomeActivity.class);
                startActivity(in);

            }
        });
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                submitUser();
            }
        });
    }
    private void submitUser(){


        String FirstName = firstname.getText().toString().trim();
        String LastName = lastname.getText().toString().trim();
        String Age = age.getText().toString().trim();
        String Weight = weight.getText().toString().trim();
        String Hight = hight.getText().toString().trim();
        String Blood = blood.getText().toString().trim();
        String Doctor= doctor.getText().toString().trim();
        String emerName = EmerName.getText().toString().trim();
        String emergencyContact = EmerContact.getText().toString().trim();
        Long Phonenumber =Long.parseLong(phonenumber.getText().toString());

        databaseReference = FirebaseDatabase.getInstance().getReference();

        if(TextUtils.isEmpty(Age) && TextUtils.isEmpty(Weight) && TextUtils.isEmpty(Hight) &&
                TextUtils.isEmpty(Doctor)&& TextUtils.isEmpty(emerName)&& TextUtils.isEmpty(emergencyContact)
                && TextUtils.isEmpty(Blood)){
            Toast.makeText(this," Please Fill The Fields Completely ",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Age.length() != 0 ){
            myNum = Integer.parseInt(Age);
            if (myNum > 150 || myNum < 18 ){
                Toast.makeText(this," Please Enter a Valid Age",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (emergencyContact.length() != 0 ){
            count = emergencyContact.length();
            if (count<10 || count>13){
                Toast.makeText(this," Please Enter a Valid Phone Number",Toast.LENGTH_SHORT).show();
                EmerContact.setText("");
                return;
            }
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            Map<String,Object> taskMap = new HashMap<String,Object>();
            //if firstName not exist this will create a new object in firebase, if exist will update

            taskMap.put("firstName", FirstName);
            taskMap.put("lastName",LastName);
            taskMap.put("age",Age);
            taskMap.put("phoneNumber",Phonenumber);
            taskMap.put("weight",Weight);
            taskMap.put("height",Hight);
            taskMap.put("blood",Blood);
            taskMap.put("doctor",Doctor);
            taskMap.put("emerName",emerName);
            taskMap.put("emerContact",emergencyContact);
            taskMap.put("genedr",geneder);


            if (user != null) {
                databaseReference.child(uid).updateChildren(taskMap);
                //displaying a success toast
                Toast.makeText(this," Profile Updated Successfully",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
