package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String name,price,brand;
//    final String ip="192.168.0.106";
    final String ip ="192.168.43.166";
    JSONObject jsonObject,responseObj;
    Button b;
    EditText edit_name,edit_price,edit_brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edit_name=findViewById(R.id.editText);


        edit_price=findViewById(R.id.editText2);


        edit_brand=findViewById(R.id.editText3);


      b=findViewById(R.id.button);
      b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              AsyProd asyProd = new AsyProd();
              asyProd.execute();

          }
      });



      }
       public class AsyProd extends AsyncTask<Void,Void,String>
       {URL url = null;

           @Override
           protected void onPreExecute() {
               super.onPreExecute();
               try {
                   jsonObject = new JSONObject();

                   url = new URL("http://"+ip+"/latest/index.php");
                   jsonObject.put("name",edit_name.getText().toString());
                   jsonObject.put("price",edit_price.getText().toString());
                   jsonObject.put("brand",edit_brand.getText().toString());

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
               try {

                   String msg = responseObj.getString("msg");


                   Intent intent = new Intent(MainActivity.this,display_activity.class);
                   startActivity(intent);
               }
               catch (Exception e) {
                                      e.printStackTrace();
                                      System.out.println(e.getMessage());
               }

           }
       }
}
