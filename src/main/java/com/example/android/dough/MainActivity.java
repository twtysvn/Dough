package com.example.android.dough;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecordsDBAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new RecordsDBAdapter(this);
        dbHelper.open();

        dbHelper.deleteAllRecords();
        dbHelper.insertSomeRecords();

        displayListView();
    }

    private void displayListView() {

        Cursor cursor = dbHelper.readAllRecords();

        String[] columnsFrom = new String[] {
                RecordsDBAdapter.COLUMN_DATE,
                RecordsDBAdapter.COLUMN_AMOUNT,
                RecordsDBAdapter.COLUMN_TYPE
        };

        int[] viewsTo = new int[] {
                R.id.text_plus_secondary,
                R.id.text_plus_amount,
                R.id.text_plus_primary
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.list_last_activity,cursor, columnsFrom, viewsTo, 0);

        final ListView listView = (ListView) findViewById(R.id.listview_last_activity);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String recordType = cursor.getString(cursor.getColumnIndexOrThrow("type"));

                Toast.makeText(getApplicationContext(), recordType, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
