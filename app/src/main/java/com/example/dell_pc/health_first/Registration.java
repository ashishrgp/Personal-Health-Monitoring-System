package com.example.dell_pc.health_first;

import android.app.Activity;
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


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends Activity {

    private static final String TAG = "EmailPassword";

    //registration fields

    EditText textBoxfirstname;
    EditText textBoxlastname;
    EditText textBoxemergency;
    EditText textBoxphone;
    EditText textBoxage;
    EditText textusername;
    EditText textemailid;
    EditText textpassword;

    String vfirstname;
    String vlastname;
    String vemergency;
    String vphone;
    double phone=0;
    int age=0;




    String vusername;
    // vusername=vusername.trim();
    String vemailid;
    //vemailid=vemailid.trim();
    String vextpassword;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    //defining a database reference
    // private DatabaseReference databaseReference;

    //our new views
    private EditText editTextName, editTextAddress;
    private Button buttonSave;


    //registration
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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
                // updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]

        textBoxfirstname=(EditText)findViewById(R.id.editTextfirstname);
        textBoxlastname=(EditText)findViewById(R.id.editTextlastname);
        textBoxemergency=(EditText)findViewById(R.id.editTextemergency);
        textBoxphone=(EditText)findViewById(R.id.editTextphonenumber);
        textBoxage=(EditText)findViewById(R.id.editTextage);
        textusername=(EditText)findViewById(R.id.editTextusername) ;
        //   textemailid=(EditText) findViewById(R.id.editTextemailid);
        textpassword=(EditText) findViewById(R.id.editTextpassword);

        vfirstname= textBoxfirstname.getText().toString();
        vlastname=textBoxlastname.getText().toString();
        vemergency=textBoxemergency.getText().toString();
        vphone=textBoxphone.getText().toString();





        vusername=textusername.getText().toString().trim();
        // vusername=vusername.trim();
        //  vemailid=textemailid.getText().toString().trim();
        //vemailid=vemailid.trim();
        vextpassword=textpassword.getText().toString().trim();





        // validation

        Button buttonsubmit=(Button) findViewById(R.id.buttonsubmit);

        buttonsubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(vphone.length()==0)
                {
                    phone=0;
                }
                else
                {
                    phone=Double.parseDouble(vphone);
                }


                String vage=textBoxage.getText().toString();

                if(vage.length()==0)
                {
                    age=0;
                }
                else
                {
                    age=Integer.parseInt(vage);
                }


                Log.d(TAG+"USERNAME :",vusername);
                Log.d(TAG+"PASSWORD ",vextpassword);


                // Toast.makeText(getApplicationContext(),"Phone value is "+vphone+" phone length is "+vphone.length(),Toast.LENGTH_SHORT).show();

                //Toast.makeText(getApplicationContext(),"age value is"+vage+"and age length is"+vage.length(),Toast.LENGTH_SHORT).show();

                //  Toast.makeText(getApplicationContext(),"is email valid "+isvalidemailid(vemailid),Toast.LENGTH_SHORT).show();

                // Toast.makeText(getApplicationContext(),"is password valid "+isvalidpassword(vextpassword),Toast.LENGTH_SHORT).show();

                // if(isvalidpassword(textpassword.getText().toString().trim()) && vphone.length()==10 && vage.length()==2 && isvalidemailid(vemailid)) {
                // Toast.makeText( this, Toast.LENGTH_SHORT).show();
                if(isvalidpassword(textpassword.getText().toString().trim()) ) {
                    Toast.makeText(getApplicationContext(),"inserting value",Toast.LENGTH_SHORT).show();

                   /* UserInformation(String firstName, String lastName,String emailId,int age,int phoneNumber,String emergencyContact,
                            String username ,String password)*/
                    //saveUserInformation(vfirstname,vlastname,vemailid,age,phone,vemergency,vusername,vextpassword);

                    createAccount(vusername,vextpassword);

                }
                else {

                    Toast.makeText(getApplicationContext(),"username is "+"test",Toast.LENGTH_SHORT).show();

                    Toast.makeText(getApplicationContext(),"password is "+vextpassword,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Invalid ", Toast.LENGTH_SHORT).show();
                }






            }


        });



        //getting the database reference
        // databaseReference = FirebaseDatabase.getInstance().getReference();


    }

    public boolean isvalidpassword(final String password){
        Pattern pattern;
        Matcher matcher;

        final String password_pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(password_pattern);
        matcher=pattern.matcher(password);

        return matcher.matches();
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



    void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        Log.d(TAG+"USERNAME :",vusername);
        Log.d(TAG+"PASSWORD ",vextpassword);
       /* if (!validateForm()) {
            return;
        }*/

        // showProgressDialog();

        // [START create_user_with_email]
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

                        // [START_EXCLUDE]
                        // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }



    private void saveUserInformation(String firstName, String lastName,String emailId,String age,double phoneNumber,String emergencyContact,
                                     String username ,String password) {

        UserInformation userInformation = new UserInformation(firstName,lastName,age,phoneNumber,username);


        //databaseReference.child(emailId).setValue(userInformation);

        //displaying a success toast
        Toast.makeText(this, " chal save kardiya teri info", Toast.LENGTH_LONG).show();
    }




}