package com.androidproject.openapiproject.Request;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidproject.openapiproject.Database.LibraryDatabaseHelper;
import com.androidproject.openapiproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    JsonObjectRequest jsonObjectRequest;
    LibraryDatabaseHelper databaseHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnRequest(View view) {
        databaseHelper = new LibraryDatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        databaseHelper.deleteAllMyData(database);

        queue = Volley.newRequestQueue(this);
        String url = "https://open.jejudatahub.net/api/proxy/aa9D99t7D8aD799aa9tt8aD8Dttt8aD8/2t49r6pro_eocbpb6ococp9c9r7629t2";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // get "data" array
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int openDate = jsonObject.getInt("openDate");
                                String libraryName = jsonObject.getString("libraryName");
                                String managementAgency = jsonObject.getString("managementAgency");
                                String address = jsonObject.getString("address");
                                String telephone = jsonObject.getString("telephone");
                                databaseHelper.insertMyData(database, openDate, libraryName, managementAgency, address, telephone);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void OnShow(View view) {
        Cursor cursor = database.query("LIBRARY", new String[] {"_id", "libraryName"},
                null, null, null, null, null); // _id column must!!!!!
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cursor,
                new String[]{"libraryName"}, new int[]{android.R.id.text1}, 0);

        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ID, id);
                startActivity(intent);
            }
        };

        list.setOnItemClickListener(itemClickListener);
    }

}