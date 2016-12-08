package com.example.minhhien.lab1;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView availableItem;
    KitchenListActivity.KitchenAdapter adapter;
    ArrayList<String> itemList;
    String samsungFridge = "";
    String samsungFreezer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        TextView descritpion = (TextView) findViewById(R.id.itemText);

        availableItem = (ListView) findViewById(R.id.availablelv);

        //itemList.add();

        availableItem.setAdapter(adapter);

        KitchenDatabaseHelper kdbHelper = new KitchenDatabaseHelper(this);
        kdbHelper.getWritableDatabase();


        availableItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentValues cv = new ContentValues();
                cv.put("KITCHEN", availableItem.getSelectedItem().toString());

                db.insert(KitchenDatabaseHelper.TABLE_NAME, "KITCHEN ITEM", cv);



                adapter.notifyDataSetChanged();
                availableItem.setAdapter(adapter);


            }
        });



    }
}
