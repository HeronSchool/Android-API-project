package com.androidproject.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonSourceRequest extends AppCompatActivity {

    public static final String TAG= "MyTag";
    RequestQueue queue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_request);

        TextView text = findViewById(R.id.text);

        queue = Volley.newRequestQueue(this);
        String url = "https://open.jejudatahub.net/api/proxy/aa9D99t7D8aD799aa9tt8aD8Dttt8aD8/2t49r6pro_eocbpb6ococp9c9r7629t2";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        text.setText("response : " + response.toString());

                        // get "data" array
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String libraryName = jsonObject.getString("libraryName");
                            text.setText("response : " + libraryName);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        text.setText("error : " + error.toString());
                    }
                });

        jsonObjectRequest.setTag(TAG);
        queue.add(jsonObjectRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(queue != null) {
            queue.cancelAll(TAG);
        }
    }
}