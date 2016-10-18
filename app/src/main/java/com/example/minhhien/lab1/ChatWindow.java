package com.example.minhhien.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    String preparemessage;
    ArrayList<String> messagehistory;
    ChatAdapter messageAdapter;
    SQLiteDatabase db;
    protected static final String ACTIVITY_NAME = "ChatWindow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        final ListView lv = (ListView) findViewById(R.id.lv);

        final EditText editTextChatbox = (EditText) findViewById(R.id.textbox);
        messagehistory = new ArrayList<>();
        Button sendButton = (Button) findViewById(R.id.sendbutton);



        ChatDatabaseHelper tempObj = new ChatDatabaseHelper(ChatWindow.this);
        db = tempObj.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME, null);

        int colIndex = cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);

        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                String message = cursor.getString(colIndex);
                messagehistory.add(message);
                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
                cursor.moveToNext();
            }
        }

        Log.i(ACTIVITY_NAME, "Cursor's Column Count" + cursor.getColumnCount());

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, i + " " + cursor.getColumnName(i));
        }

        messageAdapter = new ChatAdapter(this);
        lv.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Send Button");
                preparemessage = editTextChatbox.getText().toString();
                messagehistory.add(preparemessage);
                editTextChatbox.setText("");
                ContentValues contentValues = new ContentValues();
                contentValues.put(ChatDatabaseHelper.KEY_MESSAGE, preparemessage);
                db.insert(ChatDatabaseHelper.TABLE_NAME, "NullPlaceHolder", contentValues);

                messageAdapter.notifyDataSetChanged();
                lv.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }



    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return messagehistory.size();
        }

        @Override
        public String getItem(int position) {
            return messagehistory.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2==0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }
            else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText( getItem(position) );
            return result;
        }
    }

}
