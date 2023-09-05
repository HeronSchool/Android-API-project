package com.androidproject.openapiproject.Request;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.openapiproject.Database.LibraryDatabaseHelper;
import com.androidproject.openapiproject.Map.MapsActivity;
import com.androidproject.openapiproject.R;

import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "_id";
    public String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView openDateTextView = findViewById(R.id.openDate);
        TextView libraryNameTextView = findViewById(R.id.libraryName);
        TextView managementAgencyTextView = findViewById(R.id.managementAgency);
        TextView addressTextView = findViewById(R.id.address);
        TextView telephoneTextView = findViewById(R.id.telephone);


        Intent intent = getIntent();
        long id = (long) intent.getExtras().get(EXTRA_ID);

        SQLiteOpenHelper databaseHelper = new LibraryDatabaseHelper(this);
        try {
            SQLiteDatabase database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query("LIBRARY", new String[]{"openDate", "libraryName", "managementAgency", "address", "telephone"},
                                            "_id = ?", new String[]{String.valueOf(id)}, null, null, null);

            if (cursor.moveToFirst()) {
                int openDateText = cursor.getInt(0);
                String date = Integer.toString(openDateText);
                openDateTextView.setText("open date : " + date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8));

                libraryNameTextView.setText("library name : " + cursor.getString(1));
                managementAgencyTextView.setText("management agency : " + cursor.getString(2));

                address = cursor.getString(3);
                addressTextView.setText("address : " + address);

                String telephone = cursor.getString(4);
                telephoneTextView.setText("telephone : " + telephone.substring(0, 3) + "-" + telephone.substring(3, 6) + telephone.substring(6));
            }

        } catch (SQLException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void OnOpenMap (View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(MapsActivity.EXTRA_ADDRESS, address);
        startActivity(intent);
    }
}