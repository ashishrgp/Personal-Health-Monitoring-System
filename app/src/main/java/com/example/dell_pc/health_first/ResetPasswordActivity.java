package com.example.dell_pc.health_first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResetPasswordActivity extends Activity  {
    Button b2,b3;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ResetPasswordActivity.this,MainActivity.class);
                startActivity(in);



            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                if (isValidPassword(ed1.getText().toString().trim())&&(ed1.getText().toString().length())>=8) {
                    if((ed1.getText().toString().equals(ed2.getText().toString()))){
                        Toast.makeText(ResetPasswordActivity.this, "Successfully changed", Toast.LENGTH_SHORT).show();
                        //store new password in DB
                        Intent in=new Intent(ResetPasswordActivity.this,MainActivity.class);
                        startActivity(in);

                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Password must atleast contain more than 8 letters and an uppercase letter, a number and a digit", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}