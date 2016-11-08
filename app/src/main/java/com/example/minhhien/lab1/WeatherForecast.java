package com.example.minhhien.lab1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {

    ProgressBar progBar;
    String min;
    String max;
    String cur;
    Bitmap bm;
    String iconName;
    ImageView icon ;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        progBar = (ProgressBar) findViewById(R.id.progBar);
        icon = (ImageView) findViewById(R.id.tempPic);
        progBar.setVisibility(View.VISIBLE);
        progBar.setProgress(0);
        new ForecastQuery().execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
    }

    private class ForecastQuery extends AsyncTask<String, Integer, String> {



        @Override
        protected String doInBackground(String... params) {
              //  HttpUtils httpUtils = new HttpUtils();
                InputStream inputURL = null;
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    inputURL = conn.getInputStream();


                    try {
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        XmlPullParser xpp = factory.newPullParser();
                        xpp.setInput(inputURL, "UTF8");


                        int type = XmlPullParser.START_DOCUMENT;
                        boolean finished = false;

                        while (type != XmlPullParser.END_DOCUMENT) {

                            switch (type) {
                                case XmlPullParser.START_DOCUMENT:
                                    break;
                                case XmlPullParser.END_DOCUMENT:
                                    finished = true;
                                    break;
                                case XmlPullParser.START_TAG:
                                    String name = xpp.getName();
                                    if (name.equals("temperature")) {


                                        cur = xpp.getAttributeValue(null, "value");
                                        SystemClock.sleep(10000);
                                        publishProgress(25);
                                        min = xpp.getAttributeValue(null, "min");
                                        SystemClock.sleep(10000);
                                        publishProgress(50);
                                        max = xpp.getAttributeValue(null, "max");
                                        SystemClock.sleep(10000);
                                        publishProgress(75);

                                    }

                                    if (name.equals("weather")) {

                                        iconName = xpp.getAttributeValue(null, "icon");





                                        File file = getBaseContext().getFileStreamPath(iconName + ".png");
                                        if(file.exists()) {
                                            FileInputStream fis = null;
                                            try {
                                                fis = new FileInputStream(file);
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            bm = BitmapFactory.decodeStream(fis);

                                        } else {
                                            image = HttpUtils.getImage("http://openweathermap.org/img/w/" + iconName + ".png");
                                            FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                                            image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                            outputStream.flush();
                                            outputStream.close();
                                        }

                                        if(image != null) {
                                            publishProgress(100);
                                        }


                                    }

                                    break;
                                case XmlPullParser.END_TAG:
                                    break;
                                case XmlPullParser.TEXT:
                                    break;
                            }

                            type = xpp.next();
                        }

                    } catch (Exception e) {
                        Log.e("XML PARSIING", e.getMessage());

                    }
                } catch (Exception e) {
                    Log.e("XML PARSIING", e.getMessage());
                }
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
            TextView curTempText = (TextView) findViewById(R.id.currTemp);
                curTempText.setText("Temperature: " + cur);
            TextView minTempText = (TextView) findViewById(R.id.minTemp);
                minTempText.setText("Min: " + min);
            TextView maxTempText = (TextView) findViewById(R.id.maxTemp);
                maxTempText.setText("Max: " + max);
            icon.setImageBitmap(bm);
        }
    }

}
