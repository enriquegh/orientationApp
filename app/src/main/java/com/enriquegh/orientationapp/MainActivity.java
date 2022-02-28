package com.enriquegh.orientationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static boolean isInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isInitialized) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            isInitialized = true;

        }


        Button buttonSetPortrait = (Button)findViewById(R.id.portraitBtn);
        Button buttonSetLandscape = (Button)findViewById(R.id.landscapeBtn);
        Button buttonDefault = (Button)findViewById(R.id.defaultBtn);


        buttonSetPortrait.setOnClickListener(arg0 -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));

        buttonSetLandscape.setOnClickListener(arg0 -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));

        buttonDefault.setOnClickListener(arg0 -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR));




        OrientationEventListener mOrientationListener = new OrientationEventListener(
                getApplicationContext()) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.d("orientation","Orientation: " + orientation);
                if (orientation == 0 || orientation == 180) {
                    Log.d("orientation", "Orientation is: portrait");
                } else if (orientation == 90 || orientation == 270) {
                    Log.d("orientation", "Orientation is: landscape");

                }
            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        }


    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }






}
