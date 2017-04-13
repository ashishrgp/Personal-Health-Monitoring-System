package com.example.dell_pc.health_first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Created by ashish pc on 12-Apr-17.
 */

public class docsearch extends AppCompatActivity implements View.OnClickListener{
    private EditText searchdoc1;
    private EditText searchResult1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docsearch);

        searchdoc1=(EditText) findViewById(R.id.searchdoc1);
        searchResult1 = (EditText) findViewById(R.id.searchResult1);

        findViewById(R.id.DocSearchButton1).setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==R.id.DocSearchButton1)
        {
            /*Toast.makeText(getApplicationContext(), " search field "+searchMed.getText(),
                    Toast.LENGTH_SHORT).show();*/
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://api.betterdoctor.com/2016-03-01/doctors?location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=5ea06098f7693bf98048d39561970f4b"+searchdoc1.getText();

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.


                            JsonObject obj = new JsonParser().parse(response.toString()).getAsJsonObject();
                            // searchResult.setText("Response is: "+ obj.get("meta"));
                            JsonObject results= obj.get("doctors").getAsJsonArray().get(0).getAsJsonObject();
                            searchResult1.setText("Response is: "+ results.get("doctors"));
                          /*  for(int i=0;i<results.size();i++)
                            {
                                results.get();
                            }*/

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    searchResult1.setText("Incorrect Doctor Name");
                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);


        }

    }
}
