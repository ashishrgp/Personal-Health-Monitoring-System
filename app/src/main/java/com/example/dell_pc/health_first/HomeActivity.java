package com.example.dell_pc.health_first;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity{
    //private DrawerLayout mDrawerLayout;
    //private ActionBarDrawerToggle mToggle;
    Button b1, b2, b3, b4, b5, b6;



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
        b1 = (Button) findViewById(R.id.activity_view_profile);
        b2 = (Button) findViewById(R.id.buttonvitals);
        b3 = (Button) findViewById(R.id.buttondiet);
        b4 = (Button) findViewById(R.id.buttonmedication);
        b5 = (Button) findViewById(R.id.buttonnotepad);
        b6 = (Button) findViewById(R.id.buttonsearch);


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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(HomeActivity.this, ViewProfileActivity.class);
                startActivity(in);

            }
        });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(HomeActivity.this, vitalSigns.class);
                        startActivity(in);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Intent in = new Intent(HomeActivity.this, Diet.class);
                            startActivity(in);

                    }
                });
                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Intent in = new Intent(HomeActivity.this, Medication.class);
                            startActivity(in);

                    }
                });
                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Intent in = new Intent(HomeActivity.this, Monitoring.class);
                            startActivity(in);

                    }
                });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromEmail = "healthmanagement1234@gmail.com";
                String fromPassword = "healthmanagement1234#";
                String toEmails = "ashish_22@hotmail.com";
                String emailSubject = "EMERGENCY";
                String emailBody = "This is an emergency. Please advice the patient to take the medicine";
                new SendMailTask(HomeActivity.this).execute(fromEmail,
                        fromPassword, toEmails, emailSubject, emailBody);

              //  Intent in = new Intent(HomeActivity.this, search.class);
                //startActivity(in);

            }
        });

            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_popupmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT)
                        .show();
                mAuth.signOut();
                Intent in = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(in);



                break;
            default:
                break;
        }

        return true;
    }

        }


      /*  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        //       and use variable actionBar instead
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

  //      getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in=new Intent(HomeActivity.this,vitalSigns.class);
                startActivity(in);
            }

        });
          }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        if (mToggle.onOptionsItemSelected(Item)) {
            return true;
        }
        return super.onOptionsItemSelected(Item);

    }

*/




                          /* Intent in=new Intent(HomeActivity.this,ViewProfileActivity.class);
                           startActivity(in);*/






