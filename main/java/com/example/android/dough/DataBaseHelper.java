package com.example.android.dough;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ionutz on 3/16/2016.
 * Creare helper pentru baza de date
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DoughDB.db";
    public static final String TABLE_NAME = "records";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TYPE = "type";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, date TEXT, amount REAL, type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert new record in DB
    public boolean newRecord (String date, double amount, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("amount", amount);
        contentValues.put("type", type);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    // Get data from DB from id
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID=" + id, null);
        return results;
    }

    public ArrayList<String> getAllRecords() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        results.moveToFirst();

        while(!results.isAfterLast()) {
            array_list.add(results.getString(results.getColumnIndex(COLUMN_TYPE)));
            results.moveToNext();
        }
    return array_list;
    }
}
