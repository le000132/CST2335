package com.example.minhhien.lab1;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        TextView descritpion = (TextView) findViewById(R.id.itemText);

        final ListView availableItem = (ListView) findViewById(R.id.availablelv);

        final KitchenDatabaseHelper kdbHelper = new KitchenDatabaseHelper(this);
        kdbHelper.getWritableDatabase();



        availableItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentValues cv = new ContentValues();
                cv.put("KITCHEN", availableItem.toString());

                db.insert(KitchenDatabaseHelper.TABLE_NAME, "KITCHEN ITEM", cv);
            }
        });



    }
}
