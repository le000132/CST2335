package com.example.minhhien.lab1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Microwave extends AppCompatActivity {

    Button start;
    Button end;
    Button reset;
    EditText timer;
    String time;
    TextView microTitle;

    TextView lightsTitle;
    CountDownTimer countDownTimer;
    long timeEntered;

    final static String ACTIVITY_NAME = "Microwave";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microwave);
        Log.i(ACTIVITY_NAME, "User clicked Microwave");
        microTitle = (TextView) findViewById(R.id.microwaveTitle);
        timer = (EditText) findViewById(R.id.timer);
        final Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        final ImageView beacon = (ImageView) findViewById(R.id.redbeacon);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.epic_meal_time);
        start = (Button) findViewById(R.id.startTime);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User started the timer");
                mp.start();
                mp.setLooping(true);
                beacon.setImageResource(R.drawable.yellow_light);
                time = timer.getText().toString();
                timeEntered = Long.parseLong(time);
                countDownTimer = new CountDownTimer(timeEntered * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(millisUntilFinished / 1000));
                        start.setEnabled(false);
                        reset.setEnabled(false);
                        Log.i(ACTIVITY_NAME, "Time left: " + timer.getText().toString());

                    }

                    public void onFinish() {
                        timer.setText("Ready!");
                        mp.stop();
                        mp.setLooping(false);
                        vibrator.vibrate(500);
                        beacon.setImageResource(R.drawable.green_light);
                        start.setEnabled(true);
                        reset.setEnabled(true);
                        Log.i(ACTIVITY_NAME, "Time done");

                    }

                }.start();
            }
        });
        end = (Button) findViewById(R.id.endTime);
        end.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.stop();
                mp.setLooping(false);
                Log.i(ACTIVITY_NAME, "User ended the timer");
                beacon.setImageResource(R.drawable.red_light);
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                start.setEnabled(true);
                reset.setEnabled(true);
            }
        });
        reset = (Button) findViewById(R.id.resetTime);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.setLooping(false);
                Log.i(ACTIVITY_NAME, "User restarted the timer");
                beacon.setImageResource(R.drawable.red_light);
                timer.setText(time);
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

                android.support.v7.app.AlertDialog.Builder builder2 = new android.support.v7.app.AlertDialog.Builder(Microwave.this);
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


                Intent intent = new Intent(Microwave.this, AddActivity.class);
                startActivity(intent);


                break;

            case R.id.removeMenu:




                Intent removeIntent = new Intent(Microwave.this, RemoveActivity.class);
                startActivity(removeIntent);



                break;

            case R.id.back:

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Microwave.this);
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
