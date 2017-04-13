package com.example.dell_pc.health_first;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration1 extends AppCompatActivity implements View.OnClickListener{

    private EditText meditTextfirstname;
    private EditText meditTextlastname;
    private EditText meditTextage;
    private EditText meditTextphonenumber;
    private EditText meditTextemergency;
    private EditText muserNameField;
    private EditText mPasswordField;
    private static final String TAG = "EmailPassword";

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
        setContentView(R.layout.activity_registration1);


        meditTextfirstname = (EditText) findViewById(R.id.editTextfirstname);
        meditTextlastname = (EditText) findViewById(R.id.editTextlastname);
        meditTextage = (EditText) findViewById(R.id.editTextage);
        meditTextphonenumber = (EditText) findViewById(R.id.editTextphonenumber);
        meditTextemergency = (EditText) findViewById(R.id.editTextemergency);
        muserNameField = (EditText) findViewById(R.id.editTextusername);
        mPasswordField = (EditText) findViewById(R.id.editTextpassword);


        findViewById(R.id.buttonsubmit).setOnClickListener(this);

        findViewById(R.id.buttonback).setOnClickListener(this);

        //db inititialise
        databaseReference = FirebaseDatabase.getInstance().getReference();

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


    }


    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
       /* if (!validateForm()) {
            return;
        }*/

        //showProgressDialog();

        /*// [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            meditTextfirstname = (EditText) findViewById(R.id.editTextfirstname);
                            meditTextlastname = (EditText) findViewById(R.id.editTextlastname);
                            meditTextage = (EditText) findViewById(R.id.editTextage);
                            meditTextphonenumber = (EditText) findViewById(R.id.editTextphonenumber);
                            meditTextemergency = (EditText) findViewById(R.id.editTextemergency);
                            muserNameField = (EditText) findViewById(R.id.editTextusername);
                            mPasswordField = (EditText) findViewById(R.id.editTextpassword);
                            Toast.makeText(getApplicationContext(),
                                    "username is "+muserNameField.getText().toString()
                                            +"\n password is "+mPasswordField.getText().toString()
                                            +"\n firstname is "+meditTextfirstname.getText().toString()
                                            +"\n lastName is"+meditTextlastname.getText().toString()
                                            +"\n age is "+ Integer.parseInt(meditTextage.getText().toString())
                                            +"\n phone number"+Double.parseDouble(meditTextphonenumber.getText().toString())
                                            +"\n emergency "+meditTextemergency.getText().toString()+"\n ",

                                    Toast.LENGTH_LONG).show();

                            saveUserInformation(meditTextfirstname.getText().toString(),meditTextlastname.getText().toString(),Integer.parseInt(meditTextage.getText().toString()),
                                    Double.parseDouble(meditTextphonenumber.getText().toString())
                                    ,meditTextemergency.getText().toString(),muserNameField.getText().toString(),mPasswordField.getText().toString());


                        }

                        // [START_EXCLUDE]
                      //  hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]*/
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            meditTextfirstname = (EditText) findViewById(R.id.editTextfirstname);
                            meditTextlastname = (EditText) findViewById(R.id.editTextlastname);
                            meditTextage = (EditText) findViewById(R.id.editTextage);
                            meditTextphonenumber = (EditText) findViewById(R.id.editTextphonenumber);
                            meditTextemergency = (EditText) findViewById(R.id.editTextemergency);
                            muserNameField = (EditText) findViewById(R.id.editTextusername);
                            mPasswordField = (EditText) findViewById(R.id.editTextpassword);
                            Toast.makeText(getApplicationContext(),
                                    "username is "+muserNameField.getText().toString()
                                            +"\n password is "+mPasswordField.getText().toString()
                                            +"\n firstname is "+meditTextfirstname.getText().toString()
                                            +"\n lastName is"+meditTextlastname.getText().toString()
                                            +"\n age is "+ meditTextage.getText().toString()
                                            +"\n phone number"+Double.parseDouble(meditTextphonenumber.getText().toString()),
                                           // +"\n emergency "+meditTextemergency.getText().toString()+"\n ",

                                    Toast.LENGTH_LONG).show();

                            saveUserInformation(meditTextfirstname.getText().toString(),meditTextlastname.getText().toString(),meditTextage.getText().toString(),
                                    Double.parseDouble(meditTextphonenumber.getText().toString())
                                    ,muserNameField.getText().toString());


                        }


                    }
                });





    }





    void saveUserInformation(String firstName, String lastName,String age,double phoneNumber
                             ,String username) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String uid = user.getUid();
            UserInformation userInformation = new UserInformation(firstName,lastName,age,phoneNumber,username);


            databaseReference.child(uid).setValue(userInformation);
        }



        //displaying a success toast
        //Toast.makeText(this, " chal save kardiya teri info", Toast.LENGTH_LONG).show();
    }

    public boolean isvalidemailid(final String email){
        Pattern pattern;
        Matcher matcher;

        // final String email_pattern="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        final String email_pattern= ".+@.+\\.[a-z]+";
        pattern = Pattern.compile(email_pattern);
        matcher=pattern.matcher(email);

        return matcher.matches();
    }

    public boolean isvalidString(final String email){
        Pattern pattern;
        Matcher matcher;

        // final String email_pattern="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        final String email_pattern= "[a-zA-Z]+";
        pattern = Pattern.compile(email_pattern);
        matcher=pattern.matcher(email);

        return matcher.matches();
    }


    @Override
    public void onClick(View v) {

        String uname= muserNameField.getText().toString();
        String pass= mPasswordField.getText().toString();
        String fname= meditTextfirstname.getText().toString();
        String lname= meditTextlastname.getText().toString();
        String age= meditTextage.getText().toString();
        String phone= meditTextphonenumber.getText().toString();
//        String emergency=meditTextemergency.getText().toString();
        boolean valid =true;
        int id=v.getId();
        if(id==R.id.buttonsubmit)

        {

            if(muserNameField.getText().toString().length()!=0 && isvalidemailid(muserNameField.getText().toString()) &&  valid){

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please Enter valid email id",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }

            if(mPasswordField.getText().toString().length()>=6 && valid){

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else
            {

                Toast.makeText(getApplicationContext(), "Please Enter Valid Password(length minimum 6 characters)", Toast.LENGTH_SHORT).show();

                valid=false;
            }


            if(meditTextage.getText().toString().length()!=0 &&   ( Integer.parseInt(meditTextage.getText().toString())>=18 &&

                    Integer.parseInt(meditTextage.getText().toString())<=150 )&& valid )
            {

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else   {

                Toast.makeText(getApplicationContext(), "Please Enter Age in the range of 18-150", Toast.LENGTH_SHORT).show();

                valid=false;
            }


            if(meditTextfirstname.getText().toString().length()!=0 && isvalidString(meditTextfirstname.getText().toString())&&valid){

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else
            {

                Toast.makeText(getApplicationContext(), "Please Enter Firstname(only alphabets)",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }


            if(meditTextlastname.getText().toString().length()!=0 && valid){

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please Enter Lastname(only alphabets)",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(meditTextphonenumber.getText().toString().length()!=0 && meditTextphonenumber.getText().toString().length()==10 &&  valid){

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else  {

                Toast.makeText(getApplicationContext(), "Please Enter Phone number(10 digits)",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }

           /* if(meditTextemergency.getText().toString().length()!=0 && valid){

                // createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());

            }
            else  {

                Toast.makeText(getApplicationContext(), "Please Enter Emergency Contact(only alphabets)",
                        Toast.LENGTH_SHORT).show();
                valid=false;
            }*/


            if(valid)
            {
                createAccount(muserNameField.getText().toString(),mPasswordField.getText().toString());
                Toast.makeText(getApplicationContext(), "Redirecting...",
                        Toast.LENGTH_SHORT).show();
                Intent in=new Intent(Registration1.this,MainActivity.class);
                startActivity(in);
            }








        }
        if(id==R.id.buttonback){

            Intent in=new Intent(Registration1.this,MainActivity.class);
            startActivity(in);

        }



    }
}
