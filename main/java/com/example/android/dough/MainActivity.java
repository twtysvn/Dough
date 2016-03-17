package com.example.android.dough;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dateTime = sdf.format(c.getTime());

        int i = 1;

        DataBaseHelper myDB = new DataBaseHelper(this);

        while(i < 11) {
            Boolean recNo = myDB.newRecord(dateTime, 212.12, "Plata " + i);
            i = i + 1;
        }

        ArrayList array_list = myDB.getAllRecords();
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this,
                R.layout.list_last_activity,
                R.id.text_plus_primary,
                array_list);

        // Find reference to ListView and attach the adapter
        ListView listView = (ListView) findViewById(
                R.id.listview_last_activity);
        listView.setAdapter(arrayAdapter);
    }
}
