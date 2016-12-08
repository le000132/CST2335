package com.example.minhhien.lab1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Fridge extends AppCompatActivity {


    TextView fridgeTitle;
    TextView fridgeTemp;
    Spinner fridgeSpinner;
    ArrayAdapter<CharSequence> adapter;
    String itemSelected;
    final static String ACTIVITY_NAME = "Fridge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        Log.i(ACTIVITY_NAME, "User clicked Fridge");
        fridgeTitle = (TextView) findViewById(R.id.fridgeTitle);
        fridgeTemp = (TextView) findViewById(R.id.fridgeTemp);
        fridgeSpinner = (Spinner) findViewById(R.id.fridgeSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.fridgeTemperature, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fridgeSpinner.setAdapter(adapter);
        final ImageView icecube = (ImageView) findViewById(R.id.iceCubePic);
        fridgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = fridgeSpinner.getSelectedItem().toString();
                fridgeTemp.setText(itemSelected);
                switch (itemSelected) {
                    case "1°":
                        icecube.setImageResource(R.drawable.cubebig5);
                        break;
                    case "2°":
                        icecube.setImageResource(R.drawable.cubebig4);
                        break;
                    case "3°":
                        icecube.setImageResource(R.drawable.cubebig3);
                        break;
                    case "4°":
                        icecube.setImageResource(R.drawable.cubebig2);
                        break;
                    case "5°":
                        icecube.setImageResource(R.drawable.cubebig1);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fridgeTemp.setText(itemSelected);
            }
        });


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

                android.support.v7.app.AlertDialog.Builder builder2 = new android.support.v7.app.AlertDialog.Builder(Fridge.this);
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


                Intent intent = new Intent(Fridge.this, AddActivity.class);
                startActivity(intent);


                break;

            case R.id.removeMenu:




                Intent removeIntent = new Intent(Fridge.this, RemoveActivity.class);
                startActivity(removeIntent);



                break;

            case R.id.back:

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Fridge.this);
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
}
