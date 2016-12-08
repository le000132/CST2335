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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class Lights extends AppCompatActivity {

    Button start;
    Button end;
    Button reset;
    EditText timer;
    String time;
    TextView microTitle;
    TextView fridgeTitle;
    TextView lightsTitle;
    CountDownTimer countDownTimer;
    long timeEntered;

    final static String ACTIVITY_NAME = "Lights";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        Log.i(ACTIVITY_NAME, "User clicked Lights");
        lightsTitle = (TextView) findViewById(R.id.lightsTitle);
        final ImageView lights = (ImageView) findViewById(R.id.lightsImage);
        final SeekBar lightState = (SeekBar) findViewById(R.id.lightsSeekBar);
        lightState.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0:
                        lights.setImageResource(R.drawable.betterlights15);
                        break;
                    case 1:
                        lights.setImageResource(R.drawable.betterlights14);
                        break;
                    case 2:
                        lights.setImageResource(R.drawable.betterlights13);
                        break;
                    case 3:
                        lights.setImageResource(R.drawable.betterlights12);
                        break;
                    case 4:
                        lights.setImageResource(R.drawable.betterlights11);
                        break;
                    case 5:
                        lights.setImageResource(R.drawable.betterlights10);
                        break;
                    case 6:
                        lights.setImageResource(R.drawable.betterlights9);
                        break;
                    case 7:
                        lights.setImageResource(R.drawable.betterlights8);
                        break;
                    case 8:
                        lights.setImageResource(R.drawable.betterlights7);
                        break;
                    case 9:
                        lights.setImageResource(R.drawable.betterlights6);
                        break;
                    case 10:
                        lights.setImageResource(R.drawable.betterlights5);
                        break;
                    case 11:
                        lights.setImageResource(R.drawable.betterlights4);
                        break;
                    case 12:
                        lights.setImageResource(R.drawable.betterlights3);
                        break;
                    case 13:
                        lights.setImageResource(R.drawable.betterlights2);
                        break;
                    case 14:
                        lights.setImageResource(R.drawable.betterlights);
                        break;


                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

                android.support.v7.app.AlertDialog.Builder builder2 = new android.support.v7.app.AlertDialog.Builder(Lights.this);
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


                Intent intent = new Intent(Lights.this, AddActivity.class);
                startActivity(intent);


                break;

            case R.id.removeMenu:




                Intent removeIntent = new Intent(Lights.this, RemoveActivity.class);
                startActivity(removeIntent);



                break;

            case R.id.back:

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Lights.this);
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
