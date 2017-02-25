package com.example.dell_pc.health_first;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ForgotPasswordActivity extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // private String email = "ashish_22@hotmail.com";
    Button b1, b2, b3;
    EditText ed1;

    TextView tx1;
    int counter = 3;
    public boolean isvalidemailid(final String email){
        Pattern pattern;
        Matcher matcher;

        // final String email_pattern="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        final String email_pattern= ".+@.+\\.[a-z]+";
        pattern = Pattern.compile(email_pattern);
        matcher=pattern.matcher(email);

        return matcher.matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        ed1 = (EditText) findViewById(R.id.editText);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(in);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=ed1.getText().toString();
                if (mAuth != null && email != null &&  !email.isEmpty() && isvalidemailid(email)){
                    Log.w(" if Email authenticated", "Recovery Email has been  sent to " + email);
                    mAuth.sendPasswordResetEmail(email);
                    Toast.makeText(getApplicationContext(), "We will send you the instructions if you're a valid user", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                    startActivity(in);
                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                    Log.w(" error ", " bad entry ");
                }//ed1.getText().toString().equals(email)
                    /* if (email != null &&  !email.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Recovery Email has been sent...Redirecting to Login page", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                        startActivity(in);
                    }else{
                        Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                    }*/
            }
        });
        {
            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() == null) {

                    }
                }
                private void PassResetViaEmail() {
                    String email=ed1.getText().toString();

                    if (mAuth != null && email != null &&  !email.isEmpty()) {
                        Log.w(" if Email authenticated", "Recovery Email has been  sent to " + email);
                        mAuth.sendPasswordResetEmail(email);
                    } else {
                        Log.w(" error ", " bad entry ");
                    }
                }
            };

        }
        ;
    }

    ;

    {
         /*b3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                 if (mAuth != null) {
                    Log.w(" if Email authenticated", "Recovery Email has been  sent to " + email);
                    mAuth.sendPasswordResetEmail(email);
                } else {
                    Log.w(" error ", " bad entry ");
                }
                 if (ed1.getText().toString().equals(email)) {
                    Toast.makeText(getApplicationContext(), "Recovery Email has been sent...Redirecting to Login page", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                    startActivity(in);

                }
            }
        });*/
    }
}