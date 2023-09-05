package com.androidproject.openapiproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LibraryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDB";
    private static final int DB_VERSION = 1;

    public LibraryDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LIBRARY");
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LIBRARY");
        updateMyDatabase(sqLiteDatabase, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE LIBRARY (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "openDate INTEGER, " +
                            "libraryName TEXT, " +
                            "managementAgency TEXT, " +
                            "address TEXT, " +
                            "telephone TEXT)"
            );
        }
    }

    public void insertMyData(SQLiteDatabase sqLiteDatabase,
                             int openDate, String libraryName,
                             String managementAgency, String address,
                             String telephone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("openDate", openDate);
        contentValues.put("libraryName", libraryName);
        contentValues.put("managementAgency", managementAgency);
        contentValues.put("address", address);
        contentValues.put("telephone", telephone);
        sqLiteDatabase.insert("LIBRARY", null, contentValues);
    }

    public void deleteAllMyData(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.delete("LIBRARY",null, null);
    }
}
