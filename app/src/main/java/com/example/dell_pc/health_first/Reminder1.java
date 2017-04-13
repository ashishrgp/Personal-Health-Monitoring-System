package com.example.dell_pc.health_first;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

import static com.example.dell_pc.health_first.R.id.textView;

/**
 * Created by ashish pc on 28-Mar-17.
 */

public class Reminder1 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    String uid;
    Button b7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        b7 = (Button) findViewById(R.id.button7back);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // when create mediciation main page uncomment this
                Intent in = new Intent(Reminder1.this, HomeActivity.class);
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
                TextView tv0 = new TextView(getApplicationContext());
                tv0.setText(" S.No ");
                //tv0.setTextSize(7);
                //tv0.setPadding(10,2,10,2);
                tv0.setTextColor(Color.WHITE);
                tv0.setGravity(Gravity.CENTER);
                tbrow0.addView(tv0);

                TextView tv1 = new TextView(getApplicationContext());
                tv1.setText(" Medicine Name ");
                //tv1.setTextSize(7);
                //tv1.setPadding(10,2,10,2);
                tv1.setTextColor(Color.WHITE);
                tv1.setGravity(Gravity.CENTER);
                tbrow0.addView(tv1);

                TextView tv2 = new TextView(getApplicationContext());
                tv2.setText(" Frequency ");
                //tv2.setTextSize(7);
                //tv2.setPadding(10,2,10,2);
                tv2.setTextColor(Color.WHITE);
                tv2.setGravity(Gravity.CENTER);
                tbrow0.addView(tv2);

                stk.addView(tbrow0);
                int i=0;
                while(itr.hasNext())
                {
                    String key=itr.next().getKey();

                    TableRow tbrow = new TableRow(getApplicationContext());


                    TextView t4v = new TextView (getApplicationContext());
                    t4v.setText("" + i++);
                    t4v.setTextColor(Color.BLACK);
                    //t4v.setPadding(10,2,10,2);
                  //  t4v.setTextSize(5);
                    t4v.setGravity(Gravity.CENTER);
                    tbrow.addView(t4v);

                    final TextView  t1v = new TextView (getApplicationContext());
                    t1v.setText((String)dataSnapshot.child(key).child("Medication").getValue());
                    t1v.setTextColor(Color.BLACK);
                    //t1v.setTextSize(5);
                    //t1v.setPadding(10,2,10,2);
                    t1v.setGravity(Gravity.CENTER);
                    tbrow.addView(t1v);

                    TextView  t2v = new TextView (getApplicationContext());
                    t2v.setText(dataSnapshot.child(key).child("Frequency").getValue()+"");
                    t2v.setTextColor(Color.BLACK);
                    //t2v.setTextSize(5);
                    //t2v.setPadding(10,2,10,2);
                    t2v.setGravity(Gravity.CENTER);
                    tbrow.addView(t2v);


                    Button b1=new Button(getApplicationContext());
                    b1.setText("SET REMINDER");
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent in = new Intent(Reminder1.this, Alarm.class);

                            startActivity(in);

                        }
                    });
                    tbrow.addView(b1);

                    stk.addView(tbrow);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

        }
    public void onTableRowClick(){
        Intent in = new Intent(Reminder1.this, HomeActivity.class);
        startActivity(in);
    }

}
