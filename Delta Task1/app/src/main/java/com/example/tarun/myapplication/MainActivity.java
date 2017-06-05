package com.example.tarun.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int redcount=0;
    int bluecount=0;
    int greencount=0;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relative_layout= (RelativeLayout) findViewById(R.id.relative_layout);
        relative_layout.setBackgroundColor( Color.rgb(redcount,bluecount,greencount));
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        redcount = prefs.getInt("red", redcount);
        bluecount = prefs.getInt("blue",bluecount);
        greencount = prefs.getInt("green",greencount);
    }

    public void function(View v)
    {
        if(v.getId()==R.id.Red_button) {
            redcount += 16;
            redcount %= 256;
        }
        if(v.getId()==R.id.Blue_button) {
            bluecount += 16;
            bluecount %= 256;
        }
        if(v.getId()==R.id.Green_button) {
            greencount += 16;
            greencount %= 256;
        }
        display1(redcount);
        display2(bluecount);
        display3(greencount);

        RelativeLayout relative_layout= (RelativeLayout) findViewById(R.id.relative_layout);
        relative_layout.setBackgroundColor( Color.rgb(redcount,greencount,bluecount));
    }

    public void reset(View v)
    {
        if (v.getId()==R.id.reset)
        {
            redcount=0;
            bluecount=0;
            greencount=0;
            display1(redcount);
            display2(bluecount);
            display3(greencount);
        }
        RelativeLayout relative_layout= (RelativeLayout) findViewById(R.id.relative_layout);
        relative_layout.setBackgroundColor( Color.rgb(redcount,greencount,bluecount));
    }

    private void display1(int number)
    {
        TextView redview = (TextView) findViewById(R.id.red_view);
        redview.setText(""+number);
    }

    private void display2(int number)
    {
        TextView redview = (TextView) findViewById(R.id.blue_view);
        redview.setText(""+number);
    }

    private void display3(int number)
    {
        TextView redview = (TextView) findViewById(R.id.green_view);
        redview.setText(""+number);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("r",redcount);
        outState.putInt("g",greencount);
        outState.putInt("b",bluecount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        redcount=savedInstanceState.getInt("r");
        greencount=savedInstanceState.getInt("g");
        bluecount=savedInstanceState.getInt("b");
    }

    @Override
    protected void onResume() {
        super.onResume();
        display1(redcount);
        display2(bluecount);
        display3(greencount);
        RelativeLayout relative_layout= (RelativeLayout) findViewById(R.id.relative_layout);
        relative_layout.setBackgroundColor( Color.rgb(redcount,greencount,bluecount));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        display1(redcount);
        display2(bluecount);
        display3(greencount);
        RelativeLayout relative_layout= (RelativeLayout) findViewById(R.id.relative_layout);
        relative_layout.setBackgroundColor( Color.rgb(redcount,greencount,bluecount));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editPrefs = prefs.edit();
        editPrefs.putInt("red", redcount);
        editPrefs.putInt("blue", bluecount);
        editPrefs.putInt("green", greencount);
        editPrefs.apply();
    }

}
