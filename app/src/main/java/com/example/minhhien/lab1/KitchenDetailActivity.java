package com.example.minhhien.lab1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An activity representing a single Kitchen detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link KitchenListActivity}.
 */
public class KitchenDetailActivity extends AppCompatActivity {

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
    final static String ACTIVITY_NAME = "KitchenDetailActivity";
    Vibrator vibrator;
    LayoutInflater inflater;
    ViewGroup container;
    String itemClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(KitchenDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(KitchenDetailFragment.ARG_ITEM_ID));
            KitchenDetailFragment fragment = new KitchenDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.kitchen_detail_container, fragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        inflater = this.getLayoutInflater();
        //Display controls for kitchen items based on item clicked.here.
        itemClicked = getIntent().getStringExtra(KitchenDetailFragment.ARG_ITEM_ID);

        switch (itemClicked) {
            case "com.example.minhhien.lab1.Microwave":

//                KitchenDetailActivity kda = new KitchenDetailActivity();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.micowave, kda)
//                        .commit();
                //setContentView(R.layout.microwavelayout);
                //View microwaveView = inflater.inflate(R.layout.microwavelayout, null);
                //setContentView(microwaveView);
                /*microTitle = (TextView) microwaveView.findViewById(R.id.microwaveTitle);
                timer = (EditText) microwaveView.findViewById(R.id.timer);
                final Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                start = (Button) microwaveView.findViewById(R.id.startTime);
                start.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Log.i(ACTIVITY_NAME, "User started the timer");
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
                                vibrator.vibrate(500);
                                start.setEnabled(true);
                                reset.setEnabled(true);
                                Log.i(ACTIVITY_NAME, "Time done");

                            }

                        }.start();
                    }
                });
                end = (Button) microwaveView.findViewById(R.id.endTime);
                end.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(ACTIVITY_NAME, "User ended the timer");
                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                            countDownTimer = null;
                        }
                        start.setEnabled(true);
                        reset.setEnabled(true);
                    }
                });
                reset = (Button) microwaveView.findViewById(R.id.resetTime);
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(ACTIVITY_NAME, "User restarted the timer");
                        timer.setText(time);
                    }
                }); */

                Log.i("Testing", "com.example.minhhien.lab1.Microwave selected");
                break;
            case "Fridge":
                /*View fridgeView = inflater.inflate(R.layout.fridgelayout, null);
                setContentView(fridgeView);
                fridgeTitle = (TextView) fridgeView.findViewById(R.id.fridgeTitle);*/
                Log.i("Testing", "Fridge selected");
                break;
            case "Lights":
                View lightsView = inflater.inflate(R.layout.lightslayout, null);
                setContentView(lightsView);
                lightsTitle = (TextView) lightsView.findViewById(R.id.lightsTitle);
                final ImageView lights = (ImageView) lightsView.findViewById(R.id.lightsImage);
                final SeekBar lightState = (SeekBar) lightsView.findViewById(R.id.lightsSeekBar);
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

                Log.i("Testing", "Lights selected");
                break;
            default:
                //Set layout saying not implemented yet.
                setContentView(R.layout.activity_kitchen_detail);
                break;
        }


            /*if(getIntent().getStringExtra(KitchenDetailFragment.ARG_ITEM_ID).equals("com.example.minhhien.lab1.Microwave")) {


            }
            else if(getIntent().getStringExtra(KitchenDetailFragment.ARG_ITEM_ID).equals("Fridge")) {

                Log.i("Testing", "Fridge selected");

            }

            else if(getIntent().getStringExtra(KitchenDetailFragment.ARG_ITEM_ID).equals("Lights")) {

                Log.i("Testing", "Lights ");

            }
        */
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, KitchenListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
