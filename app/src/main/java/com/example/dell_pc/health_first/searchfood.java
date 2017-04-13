package com.example.dell_pc.health_first;

/**
 * Created by ashish pc on 12-Apr-17.
 */
        import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class searchfood extends MainActivity {

    EditText input;
    Button buttonSearch;
    WebView webView;
    String url = "http://www.google.com";
    String search_item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfood);

        input = (EditText)findViewById(R.id.input);
        buttonSearch = (Button)findViewById(R.id.search);
        webView = (WebView)findViewById(R.id.webView);

        buttonSearch.setOnClickListener(searchOnClickListener);

    }

    OnClickListener searchOnClickListener
            = new OnClickListener(){

        @Override
        public void onClick(View arg0) {
            String item = input.getText().toString();
            new JsonSearchTask(item).execute();

        }};

    private class JsonSearchTask extends AsyncTask<Void, Void, Void>{
        String itemNoSpace = input.getText().toString();
        // String item = input.getText().toString();
        String searchResult = "";
        // Your API key

        //String key="AIzaSyAEsfn3QMK77SyRn07rFTESEAmA09loVHA";

        // Your Search Engine ID
        //String cx = "015864887086491086449:k_i4z33wz70";
        // String url2 = "https://www.googleapis.com/customsearch/v1?q=" + itemNoSpace + "&key=" + key + "&cx=" + cx + "&alt=json";

        String url = "http://www.calorieking.com/foods/search.php?keywords="+itemNoSpace;
        String search_query;


        //next line explained below

        JsonSearchTask(String item){

            try {
                search_item = URLEncoder.encode(item, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            search_query = url + search_item;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                searchResult = ParseResult(sendQuery(search_query));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            buttonSearch.setEnabled(false);
            buttonSearch.setText("Wait...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {

            webView.setWebViewClient(new myWebViewClient(this));
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
            buttonSearch.setEnabled(true);
            buttonSearch.setText("Search");

            super.onPostExecute(result);
        }

    }

    private String sendQuery(String query) throws IOException{
        String result = "";

        URL searchURL = new URL(query);

        HttpURLConnection httpURLConnection = (HttpURLConnection) searchURL.openConnection();

        if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader,
                    8192);

            String line = null;
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
        }

        return result;
    }

    private String ParseResult(String json) throws JSONException{
        String parsedResult = "";

        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonObject_responseData = jsonObject.getJSONObject("responseData");
        JSONArray jsonArray_results = jsonObject_responseData.getJSONArray("results");

        parsedResult += "Google Search APIs (JSON) for : <b>" + search_item + "</b><br/>";
        parsedResult += "Number of results returned = <b>" + jsonArray_results.length() + "</b><br/><br/>";

        for(int i = 0; i < jsonArray_results.length(); i++){

            JSONObject jsonObject_i = jsonArray_results.getJSONObject(i);

            String iTitle = jsonObject_i.getString("title");
            String iContent = jsonObject_i.getString("content");
            String iUrl = jsonObject_i.getString("url");

            parsedResult += "<a href='" + iUrl + "'>" + iTitle + "</a><br/>";
            parsedResult += iContent + "<br/><br/>";
        }

        return parsedResult;
    }

    private class myWebViewClient extends WebViewClient {
        public myWebViewClient(JsonSearchTask jsonSearchTask) {
        }
    }
}