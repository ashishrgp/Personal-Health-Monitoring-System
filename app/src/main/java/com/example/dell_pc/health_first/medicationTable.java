package com.example.dell_pc.health_first;

/**
 * Created by tariq on 3/24/17.
 */
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

public class medicationTable extends AppCompatActivity {
    private DatabaseReference databaseReference;
    String uid;
    Button b7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_tabel);
        b7 = (Button) findViewById(R.id.button7back);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // when create mediciation main page uncomment this
                Intent in = new Intent(medicationTable.this, Medication.class);
                startActivity(in);
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            uid = user.getUid();
        }
        //get refrence to database
        databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("medication");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String,Object>> t = new GenericTypeIndicator<Map<String,Object>>() {};

                Iterator<DataSnapshot> itr=dataSnapshot.getChildren().iterator();
                TableLayout stk = (TableLayout) findViewById(R.id.activity_medication_table);
                TableRow tbrow0 = new TableRow(getApplicationContext());
                //stk.setPadding(0,0,0,0);
                TextView tv0 = new TextView(getApplicationContext());
                tv0.setText(" S.No ");
               //tv0.setPadding(10,2,10,2);
                //tv0.setPaddingRelative(10,20,10,20);
               //
                tv0.setGravity(Gravity.CENTER);

                tv0.setTextColor(Color.WHITE);
                tbrow0.addView(tv0);

                TextView tv1 = new TextView(getApplicationContext());
                tv1.setText(" Medicine");
               // tv1.setPaddingRelative(10,2,10,20);
                //tv1.setTextSize(7);
                tv1.setGravity(Gravity.CENTER);
                tv1.setTextColor(Color.WHITE);
                tbrow0.addView(tv1);

                TextView tv2 = new TextView(getApplicationContext());
                tv2.setText(" Frequency");
                //tv2.setPadding(10,2,10,2);
                //tv2.setTextSize(7);
                tv2.setGravity(Gravity.CENTER);
                tv2.setTextColor(Color.WHITE);
                tbrow0.addView(tv2);

                TextView tv3 = new TextView(getApplicationContext());
                tv3.setText(" Start Date");
                //tv3.setTextSize(7);
                //tv3.setPadding(0,0,0,0);
                tv3.setGravity(Gravity.CENTER);
                tv3.setTextColor(Color.WHITE);
                tbrow0.addView(tv3);

                TextView tv4 = new TextView(getApplicationContext());
                tv4.setText(" End Date");
                //tv4.setPadding(0,0,0,0);
                //tv4.setTextSize(7);
                tv4.setGravity(Gravity.CENTER);
                tv4.setTextColor(Color.WHITE);
                tbrow0.addView(tv4);

                stk.addView(tbrow0);
                int i=0;
                while(itr.hasNext())
                {
                    String key=itr.next().getKey();

                    TableRow tbrow = new TableRow(getApplicationContext());


                    EditText t4v = new EditText(getApplicationContext());
                    t4v.setText("" + i++);
                //    t4v.setPadding(10,2,10,2);
                  //  t4v.setTextSize(5);
                    t4v.setTextColor(Color.BLACK);
                    t4v.setGravity(Gravity.CENTER);
                    tbrow.addView(t4v);

                    EditText t1v = new EditText(getApplicationContext());
                    t1v.setText(dataSnapshot.child(key).child("Medication").getValue()+"".trim());
                  //  t1v.setPadding(10,2,10,2);
                    //t1v.setTextSize(5);
                    t1v.setTextColor(Color.BLACK);
                    t1v.setGravity(Gravity.CENTER);
                    tbrow.addView(t1v);

                    EditText t2v = new EditText(getApplicationContext());
                    t2v.setText(dataSnapshot.child(key).child("Frequency").getValue()+"".trim());
                    //t2v.setPadding(10,2,10,2);
                    //t2v.setTextSize(5);
                    t2v.setTextColor(Color.BLACK);
                    t2v.setGravity(Gravity.CENTER);
                    tbrow.addView(t2v);

                    EditText t3v = new EditText(getApplicationContext());
                    t3v.setText(dataSnapshot.child(key).child("StartDate").getValue()+"".trim());
                    //t3v.setPadding(0,0,0,0);
                    //t3v.setTextSize(5);
                    t3v.setTextColor(Color.BLACK);
                    t3v.setGravity(Gravity.FILL);
                    tbrow.addView(t3v);

                    EditText t5v = new EditText(getApplicationContext());
                    t5v.setText(dataSnapshot.child(key).child("EndDate").getValue()+"".trim());
                    //t5v.setPadding(0,0,0,0);
                    //t5v.setTextSize(5);
                    t5v.setTextColor(Color.BLACK);
                    t5v.setGravity(Gravity.FILL);
                    tbrow.addView(t5v);


                    stk.addView(tbrow);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }
   /* public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.activity_medication_table);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.GRAY);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(" Medic Name ");
        tv1.setTextColor(Color.GRAY);
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText(" Frequen ");
        tv2.setTextColor(Color.GRAY);
        tbrow0.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText(" StDate ");
        tv3.setTextColor(Color.GRAY);
        tbrow0.addView(tv3);

        TextView tv4 = new TextView(this);
        tv4.setText(" EdDate ");
        tv4.setTextColor(Color.GRAY);
        tbrow0.addView(tv4);

        stk.addView(tbrow0);
    }*/
}
