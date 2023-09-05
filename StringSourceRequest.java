package com.androidproject.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class StringSourceRequest extends AppCompatActivity {

    public static final String TAG= "MyTag";
    RequestQueue queue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_request);

        TextView text = findViewById(R.id.text);

        queue = Volley.newRequestQueue(this);
        String url = "http://www.google.com";
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        text.setText("response : " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        text.setText("error : " + error);
                    }
                });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(queue != null) {
            queue.cancelAll(TAG);
        }
    }
}