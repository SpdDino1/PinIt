package com.example.pinit.PinActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.pinit.PinActivity.SensorManager.SensorAngleResult;
import com.example.pinit.PinActivity.SensorManager.SensorManager;
import com.example.pinit.R;

public class PinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Hide title bar
        setContentView(R.layout.activity_pin);
    }

    public void onCaptureButtonClick(View v){
        new SensorManager(this, true, new SensorAngleResult() {
            @Override
            public void passResult(int pitch, int roll) {
                displayValues(pitch,roll);
            }
        });
    }

    public void displayValues(int pitch,int roll){
        Log.d("testLogMessage","Pitch = "+pitch);
        Log.d("testLogMessage","Roll = "+roll);
    }

}
