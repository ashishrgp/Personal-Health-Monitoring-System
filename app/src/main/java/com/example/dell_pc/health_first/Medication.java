package com.example.dell_pc.health_first;

import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import java.text.*;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Date;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.example.dell_pc.health_first.DietFields;
import com.example.dell_pc.health_first.DietTable;
import com.example.dell_pc.health_first.HomeActivity;
import com.example.dell_pc.health_first.R;
import com.google.firebase.auth.FirebaseAuth;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Medication extends AppCompatActivity implements View.OnClickListener {

    private EditText meditTextMedication,meditTextFrequency,meditTextstartDate,meditTextendDate;
    //,mstartMonth,mstartDay,mstartYear
    //      ,mendMonth,mendDay,mendYear;

    private DatabaseReference databaseReference;
    Map<String,Object> medicationMap = new HashMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();//get the current user UID
            databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("medication");
        }
        meditTextMedication = (EditText) findViewById(R.id.editTextMedication);
        meditTextFrequency = (EditText) findViewById(R.id.editTextFrequency);
        meditTextstartDate = (EditText) findViewById(R.id.edStartDate);
        meditTextendDate = (EditText) findViewById(R.id.edEndDate);


        findViewById(R.id.button1add).setOnClickListener(this);
        findViewById(R.id.button2back).setOnClickListener(this);
        findViewById(R.id.edit1).setOnClickListener(this);
        findViewById(R.id.buStartDate).setOnClickListener(this);
        findViewById(R.id.buEndDate).setOnClickListener(this);



       /*  Button startdt = (Button) findViewById(R.id.buStartDate);
        startdt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });
        Button endate = (Button) findViewById(R.id.buEndDate);
        endate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickE newFragment = new DatePickE();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });*/
    }
    @Override
    public void onClick(View v) {

        String medication= meditTextMedication.getText().toString().trim();
        String frequencies= meditTextFrequency.getText().toString().trim();
        // String starDate= meditTextstartDate.getText().toString().trim();
        // String endDate= meditTextendDate.getText().toString().trim();
        // String starDate=meditTextendDate.getText().toString();
        //String endDate=meditTextendDate.getText().toString();

        boolean valid =true;
        int id=v.getId();
        if(id==R.id.buStartDate){
            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(),"Date Picker");
        }
        if(id==R.id.buEndDate){
            DatePickE newFragment = new DatePickE();
            newFragment.show(getFragmentManager(),"Date Picker");
        }
        String starDate=meditTextstartDate.getText().toString().trim();
        String endDate=meditTextendDate.getText().toString().trim();
        if(id==R.id.edit1)
        {
            Intent in=new Intent(Medication.this,medicationTable.class);
            startActivity(in);
        }
        if(id==R.id.button1add)
        {
            //Toast.makeText(this,starDate,Toast.LENGTH_SHORT).show();
            // Toast.makeText(this,endDate,Toast.LENGTH_SHORT).show();
            System.out.println("start date "+starDate);
            System.out.println("end date "+endDate);


            if(TextUtils.isEmpty(medication) && TextUtils.isEmpty(frequencies)  &&
                    TextUtils.isEmpty(starDate)&& TextUtils.isEmpty(endDate)){
                Toast.makeText(this," Please Fill All The Fields Completely ",Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(TextUtils.isEmpty(medication)) {
                Toast.makeText(getApplicationContext(), "Please Enter valid Medication name",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
            //Date date1 = sdf.parse(starDate);
            //Date date2 = sdf.parse(endDate);
            // String stDate=startMonth + "/" + startDay + "/" + startYear;
            //  String edDate =endMonth + "/" + endDay + "/" + endYear;

            try {
                //starDate = starDate.substring(starDate.indexOf(":")+1);
                //  endDate = endDate.substring(endDate.indexOf(":")+1);
                Date date1 = sdf.parse(starDate);
                Date date2 = sdf.parse(endDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(date2);
                calendar2.set(Calendar.HOUR_OF_DAY, 0);
                calendar2.set(Calendar.MINUTE, 0);
                calendar2.set(Calendar.SECOND, 0);
                calendar2.set(Calendar.MILLISECOND, 0);


                System.out.println("****************************************");
                System.out.println("the test is"+calendar2.equals(calendar));
                //int day=date1.getDay();

                if (calendar2.before(calendar)) {
                    Toast.makeText(this,"Start date can't be after the end date ",Toast.LENGTH_SHORT).show();
                    valid=false;
                } else{ //if (date1.compareTo(date2) <= 0) {
                    valid=true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
           /* String pattern = "MM/DD/YYYY";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            //SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
                //Date date1 = sdf.parse(starDate);
                //Date date2 = sdf.parse(endDate);
                // String stDate=startMonth + "/" + startDay + "/" + startYear;
                //  String edDate =endMonth + "/" + endDay + "/" + endYear;
            //DateTimeFormatter formatter = ISODateTimeFormat.date();
            try {
                //String date12 = formatter.format(starDate);
                //String date22 = formatter.format(endDate);
                Date date1 = formatter.parse(starDate);
                Date date2 = formatter.parse(endDate);
                //Date date1 = sdf.parse(starDate);
                //Date date2 = sdf.parse(endDate);

                System.out.println("****************************************");
                System.out.println("the test is"+date2.before(date1));

                if (date1 != null && date2 != null) {
                    //if (date1.compareTo(date2)>0) {
                    if (date2.before(date1)){//.after(date2)>0) {
                        Toast.makeText(this,"Start date can't be after the end date ",Toast.LENGTH_SHORT).show();
                        valid=false;
                    } else  {//if (date1.compareTo(date2) < 0)
                        valid=true;
                    }
                }
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
            if(valid)
            {
                saveMedicationInfo(medication,frequencies,starDate,endDate);
            }
        }
        if(id==R.id.button2back){
            Intent in=new Intent(Medication.this,HomeActivity.class);
            startActivity(in);
        }
    }
    public void saveMedicationInfo(String medicationName, String frequencies, String startDate, String endDate)
    {
        medicationMap.put("Medication",medicationName);
        medicationMap.put("Frequency",frequencies);
        medicationMap.put("StartDate",startDate);
        medicationMap.put("EndDate",endDate);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference ref=databaseReference.push();
            ref.setValue(medicationMap);
            Toast.makeText(this," Medication added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

