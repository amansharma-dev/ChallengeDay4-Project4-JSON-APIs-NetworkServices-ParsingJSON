package com.example.challengeday4_project4_json_apis_networkservices_parsingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

//  object : fetch('https://jsonplaceholder.typicode.com/todos/1')
//  array  : fetch('https://jsonplaceholder.typicode.com/posts')


    public static final String TAG = "MainActivity";
    //private RequestQueue requestQueue;

    //using singleton
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestQueue = Volley.newRequestQueue(this);

        //using singleton
        requestQueue = MySingleton.getInstance(this).getRequestQueue();

        //JSON Array Request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos/1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, "onResponse: "+"Title: "+response.getString("title")+", Id: "+response.getInt("id")+", Completed: "+response.getBoolean("completed"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
            }
        });

        //JSON Array Request
        JsonArrayRequest  jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {

                       // Log.d(TAG, "onResponse: "+response);

                        for(int i = 0; i< response.length(); i++){
                            //to get the object inside the json array
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: "+jsonObject.getString("title"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        // requestQueue.add(jsonObjectRequest);

        //using singleton
        //jsonObject
        requestQueue.add(jsonObjectRequest);
        //jsonArray
        requestQueue.add(jsonArrayRequest);
    }
}
