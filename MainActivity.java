package com.androidproject.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnStringRequest(View view) {
        Intent intent = new Intent(this, StringSourceRequest.class);
        startActivity(intent);
    }

    public void OnJsonRequest(View view) {
        Intent intent = new Intent(this, JsonSourceRequest.class);
        startActivity(intent);
    }
}