package com.example.minhhien.lab1;

import android.app.Activity;
import android.content.Context;
import android.icu.util.TimeUnit;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.minhhien.lab1.dummy.DummyContent;

import java.sql.Time;

/**
 * A fragment representing a single Kitchen detail screen.
 * This fragment is either contained in a {@link KitchenListActivity}
 * in two-pane mode (on tablets) or a {@link KitchenDetailActivity}
 * on handsets.
 */
public class KitchenDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private String mItem;
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
    TextView fridgeTemp;
    Spinner fridgeSpinner;
    ArrayAdapter<CharSequence> adapter;
    String itemSelected;
    final static String ACTIVITY_NAME = "KitchenDetailFragment";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KitchenDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //Test this out to see if it works.
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

                if (appBarLayout !=null) {
                    if (mItem.equals("Microwave")) {
                        appBarLayout.setTitle("Microwave Controls");

                    } else if (mItem.equals("Fridge")) {
                        appBarLayout.setTitle("Fridge Controls");
                    } else if (mItem.equals("Lights")) {
                        appBarLayout.setTitle("Lights Controls");
                    }
                    //else if(!(mItem == "Microwave" || mItem == "Fridge" || mItem == "Lights")) {
                    //  appBarLayout.setTitle("Other items");
                    //}
                }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /* public class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECOND.toHours(millis),
                    TimeUnit.MILLISECOND.toMinutes(millis) - TimeUnit.HOUR.toHours(millis)),
            TimeUnit.MILLISECOND.toSeconds(millis) - TimeUnit.MINUTE.toSeconds(TimeUnit.MILLISECOND.toMinutes(millis));
        }

        @Override
        public void onFinish() {
            timer.setText("READY");
        }
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kitchen_detail, container, false);


        if(mItem == "Microwave") {
            View microwaveView = inflater.inflate(R.layout.microwavelayout, container, false);
            microTitle = (TextView) microwaveView.findViewById(R.id.microwaveTitle);
            timer = (EditText) microwaveView.findViewById(R.id.timer);
            final Vibrator vibrator = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            final ImageView beacon = (ImageView) microwaveView.findViewById(R.id.redbeacon);
            final MediaPlayer mp = MediaPlayer.create(this.getActivity(), R.raw.epic_meal_time);
            start = (Button) microwaveView.findViewById(R.id.startTime);
            start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.i(ACTIVITY_NAME, "User started the timer");
                    time = timer.getText().toString();
                    timeEntered = Long.parseLong(time);
                    countDownTimer = new CountDownTimer(timeEntered * 1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            beacon.setImageResource(R.drawable.yellow_light);
                            mp.setLooping(true);
                            timer.setText(String.valueOf(millisUntilFinished / 1000));
                            start.setEnabled(false);
                            reset.setEnabled(false);

                            Log.i(ACTIVITY_NAME, "Time left: " + timer.getText().toString());

                        }

                        public void onFinish() {
                            timer.setText("Ready!");
                            mp.stop();
                            vibrator.vibrate(500);
                            beacon.setImageResource(R.drawable.green_light);
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
                    beacon.setImageResource(R.drawable.red_light);
                    mp.stop();
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
                    mp.stop();
                    Log.i(ACTIVITY_NAME, "User restarted the timer");
                    beacon.setImageResource(R.drawable.red_light);
                    timer.setText(time);
                }
            });


            return microwaveView;

        }

        else if(mItem == "Fridge") {
            View fridgeView = inflater.inflate(R.layout.fridgelayout, container, false);
            fridgeTitle = (TextView) fridgeView.findViewById(R.id.fridgeTitle);
            fridgeTemp = (TextView) fridgeView.findViewById(R.id.fridgeTemp);
            fridgeSpinner = (Spinner) fridgeView.findViewById(R.id.fridgeSpinner);
            adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.fridgeTemperature, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fridgeSpinner.setAdapter(adapter);
            final ImageView icecube = (ImageView) fridgeView.findViewById(R.id.iceCubePic);
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


            return fridgeView;

        }

        else if(mItem == "Lights") {
            View lightsView = inflater.inflate(R.layout.lightslayout, container, false);
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

            return lightsView;
        }

        else {
            return null;
        }
        // Show the dummy content as text in a TextView.
       // if (mItem != null) {
         //   ((TextView) rootView.findViewById(R.id.kitchen_detail)).setText(mItem);
        //}
       // return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
