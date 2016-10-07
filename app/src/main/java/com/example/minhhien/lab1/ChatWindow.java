package com.example.minhhien.lab1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        ListView lv = (ListView) findViewById(R.id.lv);

        final EditText editTextChatbox = (EditText) findViewById(R.id.textbox);
        messagehistory = new ArrayList<>();
        Button sendButton = (Button) findViewById(R.id.sendbutton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preparemessage = editTextChatbox.getText().toString();
                messagehistory.add(preparemessage);
                editTextChatbox.setText("");
                messageAdapter.notifyDataSetChanged();
            }
        });

        messageAdapter = new ChatAdapter( this );
        lv.setAdapter(messageAdapter);

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
