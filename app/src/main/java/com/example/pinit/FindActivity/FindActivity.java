package com.example.pinit.FindActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.pinit.R;
import com.example.pinit.SensorManager.SensorAngleResult;
import com.example.pinit.SensorManager.SensorManager;

public class FindActivity extends AppCompatActivity {
    private int offsetAzimuth; //Offset has 180 added to it
    private int offsetRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Hide title bar
        setContentView(R.layout.activity_find);

        Intent intent=getIntent();
        offsetAzimuth=intent.getIntExtra("azimuth",0);
        offsetRoll=intent.getIntExtra("roll",0);

        new SensorManager(this, false, new SensorAngleResult() {
            @Override
            public void passResult(int azimuth, int roll) {
                Log.d("testLogMessage","azimuth: "+(azimuth+180));
                Log.d("testLogMessage","Roll: "+(roll+180));
            }
        });
    }
}
