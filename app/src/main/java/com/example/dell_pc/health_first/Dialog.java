package com.example.dell_pc.health_first;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ashish pc on 28-Mar-17.
 */

public class Dialog extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alarm);


        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                Dialog.this);

// Setting Dialog Title
        alertDialog2.setTitle("Its Time to Take your Medicine");

// Setting Dialog Message
        alertDialog2.setMessage("Medicine Name:  Crocin \nDosage: 1 Tablet \nInstructions: Please take with water");


// Setting Icon to Dialog


// Setting Positive "Yes" Btn

        alertDialog2.setPositiveButton("TAKEN",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Intent in=new Intent(Dialog.this,HomeActivity.class);
                        startActivity(in);
                        dialog.cancel();
                    }
                });


// Setting Negative "NO" Btn
        alertDialog2.setNegativeButton("NOT TAKEN",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        String fromEmail = "healthmanagement1234@gmail.com";
                        String fromPassword = "healthmanagement1234#";
                        String toEmails = "ashish_22@hotmail.com";
                        String emailSubject = "EMERGENCY";
                        String emailBody = "This is an emergency. Please advice the patient to take the medicine";
                        new SendMailTask(Dialog.this).execute(fromEmail,
                                fromPassword, toEmails, emailSubject, emailBody);



/*
                        final GMailSender sender = new GMailSender();
                        new AsyncTask<Void, Void, Void>() {
                            @Override public Void doInBackground(Void... arg) {
                                try {
                                    sender.sendMail("EMERGENCY",
                                            "The patient has not taken his medicine. Please advice", "ashish_22@hotmail.com");
                                } catch (Exception e) {
                                    Log.e("SendMail", e.getMessage(), e);
                                }
                                return null;
                            }

                        }.execute();


*/



                        AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(
                                Dialog.this);

// Setting Dialog Title
                        alertDialog3.setTitle("Medication Missed Alert!");

// Setting Dialog Message
                        alertDialog3.setMessage("An Email has been sent to the Emergency Contact Listed ");
                        onPause();
                        alertDialog3.setPositiveButton("Acknowledge",

                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Write your code here to execute after dialog
                                        Intent in=new Intent(Dialog.this,HomeActivity.class);
                                        startActivity(in);



                                        dialog.cancel();
                                    }
                                });
                        // Write your code here to execute SMS and EMail Services here after dialog
                        alertDialog3.show();

                    }
                });
// Showing Alert Dialog
        alertDialog2.show();
    }

}
