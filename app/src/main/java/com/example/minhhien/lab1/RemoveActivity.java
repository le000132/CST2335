package com.example.minhhien.lab1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class RemoveActivity extends AppCompatActivity {

    SQLiteDatabase db;
    KitchenListActivity.KitchenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        KitchenDatabaseHelper kdbHelper = new KitchenDatabaseHelper(this);
        db = kdbHelper.getWritableDatabase();


        TextView description = (TextView) findViewById(R.id.currentItemText);

        final ListView currentItemlv = (ListView) findViewById(R.id.kitchenlv);

        currentItemlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Delete item here
                db.delete(KitchenDatabaseHelper.TABLE_NAME, KitchenDatabaseHelper.KEY_ID  = "?", new String[] { Long.toString(id)});
                Cursor result = db.query(false, KitchenDatabaseHelper.TABLE_NAME, new String[]{"ID", "KITCHEN"},
                        null, null, null, null, null, null);
                currentItemlv.setAdapter(adapter);

            }
        });
    }
}
