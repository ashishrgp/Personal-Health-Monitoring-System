package com.example.dell_pc.health_first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class SearchService extends AppCompatActivity implements View.OnClickListener{

    private EditText searchMed;
    private TextView purpose;
    private TextView when_using;
    private TextView indications_and_usage;
    private  TextView label;
    private WebView dosage_and_administration_table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        searchMed=(EditText) findViewById(R.id.searchMed);

        purpose=(TextView) findViewById(R.id.purpose);

        when_using=(TextView) findViewById(R.id.when_using);

        label=(TextView) findViewById(R.id.label);

        indications_and_usage=(TextView) findViewById(R.id.indications_and_usage);

        dosage_and_administration_table=(WebView) findViewById(R.id.dosage_and_administration_table);

        findViewById(R.id.MedSearchButton).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

        if(id==R.id.MedSearchButton)
        {
            /*Toast.makeText(getApplicationContext(), " search field "+searchMed.getText(),
                    Toast.LENGTH_SHORT).show();*/
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://api.fda.gov/drug/label.json?search=generic_name:"+searchMed.getText();

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

                            purpose.setVisibility(View.VISIBLE);
                            when_using.setVisibility(View.VISIBLE);
                            indications_and_usage.setVisibility(View.VISIBLE);
                            label.setVisibility(View.VISIBLE);
                            dosage_and_administration_table.setVisibility(View.VISIBLE);

                            JsonObject obj = new JsonParser().parse(response.toString()).getAsJsonObject();
                            // searchResult.setText("Response is: "+ obj.get("meta"));
                            JsonObject results= obj.get("results").getAsJsonArray().get(0).getAsJsonObject();

                            String sPurpose=results.get("purpose").toString();
                            purpose.setText(sPurpose.substring(2,sPurpose.length()-3));

                            String swhen_using=results.get("when_using").toString();
                            when_using.setText("when_using: "+ swhen_using.substring(2,swhen_using.length()-3));

                            String sindications_and_usage=results.get("indications_and_usage").toString();
                            indications_and_usage.setText("indications_and_usage: "+sindications_and_usage.substring(2,sindications_and_usage.length()-3));

                            String sTable=results.get("dosage_and_administration_table").toString();

                            dosage_and_administration_table.loadDataWithBaseURL(null,sTable.substring(2,sTable.length()-3),
                                    "text/html", "utf-8", null);



                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // purpose.setText("Incorrect medicine Name");
                    purpose.setVisibility(View.INVISIBLE);
                    when_using.setVisibility(View.INVISIBLE);
                    indications_and_usage.setVisibility(View.INVISIBLE);
                    label.setVisibility(View.INVISIBLE);
                    dosage_and_administration_table.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Enter Valid Generic Medicine Name",
                            Toast.LENGTH_SHORT).show();

                }
            });
// Add the request to the RequestQueue.
            queue.add(stringRequest);


        }

    }
}
