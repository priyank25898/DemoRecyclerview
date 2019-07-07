package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class display_activity extends AppCompatActivity {

RecyclerView recyclerView ;
//    final String ip="192.168.0.106";
final String ip ="192.168.43.166";
    JSONObject jsonObject,responseObj;

    ArrayList<display> displayArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activity);
        recyclerView = findViewById(R.id.recyclerview);

AsyFetch asyFetch = new AsyFetch();
asyFetch.execute();

    }

    public class AsyFetch extends AsyncTask<Void,Void,String>
    {
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


                try {
                    jsonObject = new JSONObject();
                    url = new URL("http://"+ip+"/latest/select.php");

                } catch (Exception e) {
                    e.printStackTrace();

                }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try
            {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type","json/application");
                urlConnection.setRequestProperty("accept","json/application");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                DataOutputStream dout = new DataOutputStream(urlConnection.getOutputStream());
                dout.write(jsonObject.toString().getBytes());
                if(urlConnection.getResponseCode() ==  200)
                {
                    String response_message=null;
                    StringBuilder stringBuilder = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    while ((response_message = bufferedReader.readLine())!= null)
                    {
                        stringBuilder.append(response_message);
                    }
                    responseObj = new JSONObject(stringBuilder.toString());

                }


            }
            catch (Exception e)
            {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray result;
            try{
                result = responseObj.getJSONArray("result");
                displayArrayList = new ArrayList<display>();
                for (int i=0;i<result.length();i++)
                {
                    JSONObject object = new JSONObject();
                    object = result.getJSONObject(i);
                    display obj = new display(object.getString("name"),object.getInt("price"),object.getString("brand"));

                    //  getDataTA.setDepartment(object.getString("department_name"));
                    displayArrayList.add(obj);
                }

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(display_activity.this);
                recyclerView.setLayoutManager(mLayoutManager);

                RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(displayArrayList,display_activity.this);
                recyclerView.setAdapter(recyclerviewAdapter);

            }
            catch (Exception e)
            {
                Toast.makeText(display_activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }
    }
}
