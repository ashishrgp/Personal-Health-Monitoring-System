package com.example.dell_pc.health_first;

/**
 * Created by ashish pc on 12-Apr-17.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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

public class notepad extends AppCompatActivity implements View.OnClickListener {
   // private ListView mListViewNotes;
    private DatabaseReference databaseReference;
    String uid;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //get the current user UID
        if(user!=null)
        {
            uid = user.getUid();
        }

        //findViewById(R.id.edit).setOnClickListener(this);

        //get refrence to database
        databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("notes");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( final DataSnapshot dataSnapshot) {

                   /* GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                    List messages = dataSnapshot.getValue(t);*/

                GenericTypeIndicator<Map<String,Object>> t = new GenericTypeIndicator<Map<String,Object>>() {};
                // Map messages = dataSnapshot.getChildren();

                Iterator<DataSnapshot> itr=dataSnapshot.getChildren().iterator();
                final TableLayout stk = (TableLayout) findViewById(R.id.table_main);
                TableRow tbrow0 = new TableRow(getApplicationContext());
              /*  TextView tv0 = new TextView(getApplicationContext());
                tv0.setText(" Sl.No ");
                tv0.setTextColor(Color.GRAY);
                tbrow0.addView(tv0);*/
                TextView tv1 = new TextView(getApplicationContext());
                tv1.setText(" Title ");
                tv1.setTextColor(Color.GRAY);
                tbrow0.addView(tv1);
                TextView tv2 = new TextView(getApplicationContext());
                tv2.setText(" Content ");
                tv2.setTextColor(Color.GRAY);
                tbrow0.addView(tv2);
               /* TextView tv3 = new TextView(getApplicationContext());
                tv3.setText(" Calories ");
                tv3.setTextColor(Color.GRAY);
                tbrow0.addView(tv3);*/
                stk.addView(tbrow0);
                int i=0;
                int total=0;



                while(itr.hasNext())
                {
                    final String key=itr.next().getKey();

                   // String tempfoodname = (String)dataSnapshot.child(key).child("foodname").getValue();
                    String title = dataSnapshot.child(key).child("title").getValue()+"";
                    String content = dataSnapshot.child(key).child("content").getValue()+"";

                    // table formation

                    TableRow tbrow = new TableRow(getApplicationContext());


                    EditText t1v = new EditText(getApplicationContext());
                    t1v.setText((String)dataSnapshot.child(key).child("title").getValue());
                    t1v.setTextColor(Color.BLACK);
                    t1v.setGravity(Gravity.CENTER);
                    t1v.setId(i);
                    tbrow.addView(t1v);



                    EditText t2v = new EditText(getApplicationContext());
                    t2v.setText(dataSnapshot.child(key).child("content").getValue()+"");
                    t2v.setTextColor(Color.BLACK);
                    t2v.setGravity(Gravity.CENTER);
                    t1v.setId(i+1);
                    tbrow.addView(t2v);
                   // total+=Integer.parseInt(dataSnapshot.child(key).child("content").getValue()+"");

                /*    EditText t3v = new EditText(getApplicationContext());
                    t3v.setText(dataSnapshot.child(key).child("servingSize").getValue()+"");
                    t3v.setTextColor(Color.BLACK);
                    t3v.setGravity(Gravity.CENTER);
                    t1v.setId(i+2);
                    tbrow.addView(t3v);*/

                    Button btn = new Button(getApplicationContext());
                    btn.setText("DEL");
                    btn.setId(i+2);
                    btn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            System.out.println("v.getid is:- " + v.getId());

                            databaseReference.child(key).removeValue();
                            Intent in=new Intent(notepad.this,notepad.class);
                            startActivity(in);




                            //_hRedraw.sendEmptyMessage(0);

                        }
                    });

                    tbrow.addView(btn);


                    stk.addView(tbrow);

                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }






        });

        // init();
        // not my code
       // mListViewNotes=(ListView) findViewById(R.id.main_listview);
        findViewById(R.id.button_edit).setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;

    }

    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.button_edit)
        {

            /*Toast.makeText(getApplicationContext(), "edit button pressed",
                    Toast.LENGTH_SHORT).show();*/

            Intent in=new Intent(notepad.this,HomeActivity.class);
            startActivity(in);


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
                Intent newNoteActivity=new Intent(this,NoteActivity.class);
                startActivity(newNoteActivity);
                break;

        }
        return true;
    }


}
