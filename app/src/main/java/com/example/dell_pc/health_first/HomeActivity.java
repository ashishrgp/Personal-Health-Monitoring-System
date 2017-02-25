package com.example.dell_pc.health_first;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends Activity {
    Button b1;
    private static final String TAG = "EmailPassword";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                //  updateUI(user);
                // [END_EXCLUDE]
            }
        };

        b1 = (Button)findViewById(R.id.button);

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu=new PopupMenu(getApplicationContext(),v);

              // MenuInflater menuInflater=popupMenu.getMenuInflater();
               popupMenu.inflate(R.menu.activity_popupmenu);
               popupMenu.show();

               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       Toast.makeText(getApplicationContext(),item.toString(),Toast.LENGTH_SHORT).show();
                       if(item.toString().equals("Profile")){
                           Intent in=new Intent(HomeActivity.this,ViewProfileActivity.class);
                           startActivity(in);

                       }
                       if(item.toString().equals("Vital Signs")){
                           Intent in=new Intent(HomeActivity.this,vitalSigns.class);
                            startActivity(in);

                       }
                       if(item.toString().equals("Logout")){

                          /* Intent in=new Intent(HomeActivity.this,ViewProfileActivity.class);
                           startActivity(in);*/

                           mAuth.signOut();
                           Intent in=new Intent(HomeActivity.this,MainActivity.class);
                           startActivity(in);

                       }
                       return true;
                   }
               });
           }
       });



    }
}
