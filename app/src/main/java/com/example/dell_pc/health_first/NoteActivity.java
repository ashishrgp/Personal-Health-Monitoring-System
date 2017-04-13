package com.example.dell_pc.health_first;

/**
 * Created by ashish pc on 12-Apr-17.
 */



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.util.Map;


public class NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText title;
    private EditText content;


    Map<String, Object> notesMap = new HashMap<String, Object>();

    //database
    private DatabaseReference databaseReference;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();//get the current user UID
            Toast.makeText(getApplicationContext(), "Please Enter the notes",
                    Toast.LENGTH_SHORT).show();

            databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("notes");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    GenericTypeIndicator<Map<String, Object>> t = new GenericTypeIndicator<Map<String, Object>>() {
                    };
                    // Map messages = dataSnapshot.getChildren();

                    Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();

                    while (itr.hasNext()) {
                        String key = itr.next().getKey();

                        String title = (String) dataSnapshot.child(key).child("title").getValue();
                        String content = dataSnapshot.child(key).child("content").getValue() + "";


                    }

                    findViewById(R.id.button_back).setOnClickListener(NoteActivity.this);
                    findViewById(R.id.button_save_note).setOnClickListener(NoteActivity.this);;
                    title=(EditText) findViewById(R.id.title);

                    content=(EditText) findViewById(R.id.content);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }


    /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null) {
        String uid = user.getUid();//get the current user UID
        Toast.makeText(getApplicationContext(), "Please Enter Valid Food name",
                Toast.LENGTH_SHORT).show();*/

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_note_save:
               // savenotesInfo(title, content);
                Toast.makeText(getApplicationContext(), "Saved!!",
                        Toast.LENGTH_SHORT).show();

                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_save, menu);
        return true;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        boolean valid = true;
        String stitle = title.getText().toString();
        String scontent = content.getText().toString();
        if (id == R.id.button_edit) {

            /*Toast.makeText(getApplicationContext(), "edit button pressed",
                    Toast.LENGTH_SHORT).show();*/

          /*  Intent in = new Intent(NoteActivity.this, MainActivity.class);
            startActivity(in);

            if (valid) {

                savenotesInfo(title, content);


            }*/
        }

        if (id == R.id.button_save_note) {

            /*Toast.makeText(getApplicationContext(), "edit button pressed",
                    Toast.LENGTH_SHORT).show();*/

            //Intent inte = new Intent(NoteActivity.this, NoteActivity.class);
            //startActivity(inte);

           // if (valid) {

            Toast.makeText(getApplicationContext(), "Tilte"+stitle,
                    Toast.LENGTH_SHORT).show();

            Toast.makeText(getApplicationContext(), "Content"+scontent,
                    Toast.LENGTH_SHORT).show();

                savenotesInfo(stitle, scontent);


            //}



        }
        if (id == R.id.button_back) {
            Intent intent = new Intent(this, notepad.class);
            startActivity(intent);
        }


    }


    private void savenotesInfo(String title, String content) {
        //NotesField field = new NotesField(title, content);
        //foodList.add(field);

        // foodMap.put("","")



        notesMap.put("title", title);
        notesMap.put("content", content);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

           // Toast.makeText(this,"uid while saving data is "+user.getUid(),Toast.LENGTH_SHORT).show();
            System.out.println ("UID is"+user.getUid());
            String uid = user.getUid();//get the current user UID
            // Toast.makeText(this,"uid while saving data is "+uid,Toast.LENGTH_SHORT).show();
            //databaseReference.updateChildren(dietvals);


            DatabaseReference ref = databaseReference.push();
            ref.setValue(notesMap);

            //Toast.makeText(NoteActivity.this, " notes Updated Successfully", Toast.LENGTH_SHORT).show();

           /*
            Intent in = new Intent(NoteActivity.this, MainActivity.class);
            startActivity(in);*/

        }
    }
}
