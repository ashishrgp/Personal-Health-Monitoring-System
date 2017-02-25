package com.example.dell_pc.health_first;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {
    Button b1,b2,b3;
    EditText ed1,ed2;
    private static final String TAG = "EmailPassword";
    TextView tx1;
    int counter = 3;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button1);
        b3 =(Button)findViewById(R.id.button3);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

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
            public void onClick(View v) {
                signIn(ed1.getText().toString(),ed2.getText().toString());

            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(in);
            }
        });





       b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in=new Intent(MainActivity.this,Registration1.class);
                startActivity(in);
            }

        });



    }

    private void signIn(String email, String password) {
      /*  Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }*/

        //  showProgressDialog();
        //Toast.makeText(MainActivity.this, "Email is"+email
                //+"\n password is "+password, Toast.LENGTH_SHORT).show();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "success",
                                    Toast.LENGTH_SHORT).show();
                            Intent in=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(in);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                           // mStatusTextView.setText(R.string.auth_failed);
                            Toast.makeText(getApplicationContext(), "success",
                                    Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Intent in=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(in);
                        }
                        // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

}
