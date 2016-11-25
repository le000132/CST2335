package com.example.minhhien.lab1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.example.minhhien.lab1.dummy.DummyContent;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Kitchen. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link KitchenDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class KitchenListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    ProgressBar progBar;

    private boolean mTwoPane;
    String kitchenItem;
    String microwave = "Microwave";
    String fridge = "Fridge";
    String lights = "Lights";
    private String message = "You selected item 1";
    ArrayList<String> kitchenlist;
    KitchenAdapter messageAdapter;
    SQLiteDatabase db;
    protected static final String ACTIVITY_NAME = "Kitchen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        progBar = (ProgressBar) findViewById(R.id.kitchen_progBar);
        progBar.setVisibility(View.VISIBLE);
        progBar.setProgress(0);
        KitchenQuery kq = new KitchenQuery(KitchenListActivity.this);
        kq.execute();


        // setContentView(R.layout.activity_chat_window);
        final ListView lv = (ListView) findViewById(R.id.kitchenlv);

        final EditText editTextChatbox = (EditText) findViewById(R.id.kitchen_textbox);
        kitchenlist = new ArrayList<>();

        Button searchButton = (Button) findViewById(R.id.searchButton);


        messageAdapter = new KitchenAdapter(this);

        kitchenlist.add(microwave);
        kitchenlist.add(fridge);
        kitchenlist.add(lights);

        lv.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();
        lv.setAdapter(messageAdapter);


        //Button to search for items.
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked search button");
                kitchenItem = editTextChatbox.getText().toString();
                Cursor result = db.rawQuery("SELECT * FROM ? WHERE KITCHEN = ?", new String[]{KitchenDatabaseHelper.TABLE_NAME, kitchenItem} );

                String columnNames[] = result.getColumnNames();

                for (String colName : columnNames) {
                    System.out.println("Name: " + colName);
                }

                int nameColumnIndex = result.getColumnIndex(KitchenDatabaseHelper.KEY_MESSAGE);

                while (!result.isAfterLast()) {
                    String thisName = result.getString(nameColumnIndex);
                    System.out.println(thisName);

                    result.moveToNext();

                    result.getColumnNames();

                    int numResult = result.getCount();

                }

                if(kitchenItem == "") {
                    AlertDialog.Builder builder = new AlertDialog.Builder(KitchenListActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Missing Arguments");
                    builder.setMessage("Please insert the item name you want to search for.");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
                //messageAdapter.notifyDataSetChanged();
                //lv.setAdapter(messageAdapter);

                //Add the items via toolbar, not this below
                //messagehistory.add(kitchenItem);

                //ContentValues contentValues = new ContentValues();
                //contentValues.put(KitchenDatabaseHelper.KEY_MESSAGE, preparemessage);
                //db.insert(KitchenDatabaseHelper.TABLE_NAME, "NullPlaceHolder", contentValues);

                //messageAdapter.notifyDataSetChanged();
                //lv.setAdapter(messageAdapter);
                //messageAdapter.notifyDataSetChanged();
            }
        });

       // messageAdapter.notifyDataSetChanged();
       // lv.setAdapter(messageAdapter);




        if (findViewById(R.id.kitchen_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    //  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    //    recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    //}

//    public class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final List<DummyContent.DummyItem> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.message_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mTwoPane) {
//                        Bundle arguments = new Bundle();
//                        arguments.putString(MessageDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                        MessageDetailFragment fragment = new MessageDetailFragment();
//                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.message_detail_container, fragment)
//                                .commit();
//                    } else {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, MessageDetailActivity.class);
//                        intent.putExtra(MessageDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//
//                        context.startActivity(intent);
//                    }
//                }
//            });
//        }


    public int getItemCount() {
        return kitchenlist.size();
    }


    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.kitchen_toolbar_menu, m);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {


        switch (mi.getItemId()) {

            //Go to living room activity
            case R.id.activity_living_room:

                /*Replace with

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(KitchenListActivity.this);
                builder.setTitle("Go to Living Room interface?");
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(KitchenListActivity.this, LivingRoomListActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();

                */


                break;
            case R.id.activity_kitchen:

                /*
                Replace with custom dialog that says "You are in the current interface." for those who are in the activity relative to the icon.
                 */

                android.support.v7.app.AlertDialog.Builder builder2 = new android.support.v7.app.AlertDialog.Builder(KitchenListActivity.this);
                builder2.setTitle("Current Interface"); //Set message to This is the current interface that you are in.
                LayoutInflater inflater = this.getLayoutInflater();
                View view = inflater.inflate(R.layout.customkitchenlayout, null);
                builder2.setView(view);
                final TextView dialogText = (TextView) view.findViewById(R.id.customKitchenDialog);
                Resources res = getResources();
                String text = String.format(res.getString(R.string.current), ACTIVITY_NAME);
                dialogText.setText(text);
                builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

                android.support.v7.app.AlertDialog build = builder2.create();
                build.show();


                break;

            case R.id.activity_house:

                /*Replace with


                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(KitchenListActivity.this);
                builder.setTitle("Go to Living Room interface?");
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(KitchenListActivity.this, HouseListActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();
                */


                break;

            case R.id.activity_automobile:

                /*Replace with

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(KitchenListActivity.this);
                builder.setTitle("Go to Living Room interface?");
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(KitchenListActivity.this, AutomobileListActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();

                */

                break;

            case R.id.aboutKitchenMenu:
                //Toast toast = Toast.makeText(getApplicationContext(), "Version 1.0, by Frederic Le", Toast.LENGTH_LONG);//this is the ListActivity
                //toast.show();
                break;

            case R.id.addMenu:


                        Intent intent = new Intent(KitchenListActivity.this, AddActivity.class);
                        startActivity(intent);


                break;

            case R.id.removeMenu:




                        Intent removeIntent = new Intent(KitchenListActivity.this, RemoveActivity.class);
                        startActivity(removeIntent);



                break;

            case R.id.back:

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(KitchenListActivity.this);
                builder.setTitle("Do you want to go back?");
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();

                break;

        }
        return true;
    }
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mIdView;
//            public final TextView mContentView;
//            public DummyContent.DummyItem mItem;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mIdView = (TextView) view.findViewById(R.id.id);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//        }
//    }

    protected class KitchenAdapter extends ArrayAdapter<String> {
        public KitchenAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return kitchenlist.size();
        }

        @Override
        public String getItem(int position) {
            return kitchenlist.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = KitchenListActivity.this.getLayoutInflater();
            View result = null;
            result = inflater.inflate(R.layout.chat_row_incoming, null);
           // if (position % 2 == 0) {
             //   result = inflater.inflate(R.layout.chat_row_incoming, null);
            //} else {
              //  result = inflater.inflate(R.layout.chat_row_outgoing, null);
            //}

            TextView message = (TextView) result.findViewById(R.id.message_text);
            final String messageText = getItem(position);
            message.setText(messageText);
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(KitchenDetailFragment.ARG_ITEM_ID, messageText);
                        KitchenDetailFragment fragment = new KitchenDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.kitchen_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, KitchenDetailActivity.class);
                        intent.putExtra(KitchenDetailFragment.ARG_ITEM_ID, messageText);

                        context.startActivity(intent);
                    }
                }


            });
            return result;
        }
    }

    private class KitchenQuery extends AsyncTask<String, Integer, String> {

        Context ctx;
        KitchenQuery(Context ctx) {

            this.ctx = ctx;

        }


        @Override
        protected String doInBackground(String... params) {

            KitchenDatabaseHelper tempObj = new KitchenDatabaseHelper(ctx);
            db = tempObj.getWritableDatabase();
            //  HttpUtils httpUtils = new HttpUtils();

            publishProgress(25);

            //Load table
            Cursor cursor = db.rawQuery("SELECT * FROM " + KitchenDatabaseHelper.TABLE_NAME, null);

            publishProgress(50);

            int colIndex = cursor.getColumnIndex(KitchenDatabaseHelper.KEY_MESSAGE);

            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {

                    String message = cursor.getString(colIndex);
                    kitchenlist.add(message);
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(KitchenDatabaseHelper.KEY_MESSAGE)));
                    cursor.moveToNext();
                }
            }

            publishProgress(75);

            Log.i(ACTIVITY_NAME, "Cursor's Column Count" + cursor.getColumnCount());

            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.i(ACTIVITY_NAME, i + " " + cursor.getColumnName(i));
            }
            cursor.close();

            return null;




        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            try {
                super.onProgressUpdate();
                progBar.setProgress(values[0]);

            }
            catch (Exception e) {
            }
        }

        @Override
        protected void onPostExecute(String s) {

            progBar.setVisibility(View.GONE);
            Toast.makeText(ctx, s, Toast.LENGTH_LONG);

        }
    }
}
