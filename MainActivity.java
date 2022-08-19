package com.androidproject.plandemotutorial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // dialog
    View dialogView;
    Spinner spinnerCategory;
    ArrayAdapter spinnerAdapter;
    Button btnAdd, btnDelete;

    // plan list
    ListView lvPlan;
    ArrayAdapter planAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        lvPlan = findViewById(R.id.lv_plan);

        // add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog_option, null);

                // layout
                //LinearLayout layoutCategory = dialogView.findViewById(R.id.layout_category);

                // spinner
                spinnerCategory = dialogView.findViewById(R.id.spn_category);

                SetSpinner(R.array.dialog_category, spinnerCategory);

                // builder
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setView(dialogView);
                dlg.show();

                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void SetSpinner(int d, Spinner spinner) {
        spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(d));
        spinner.setAdapter(spinnerAdapter);
    }
}