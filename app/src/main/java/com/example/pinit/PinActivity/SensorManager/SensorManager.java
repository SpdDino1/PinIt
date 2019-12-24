package com.example.pinit.PinActivity.SensorManager;

import android.content.Context;
import android.util.Log;

import com.example.pinit.PinActivity.PinActivity;
import com.example.pinit.PinActivity.SensorManager.Sensor.Accelerometer;
import com.example.pinit.PinActivity.SensorManager.Sensor.Magnetometer;

public class SensorManager {
    private static float[] magnetometerReading;
    private static float[] accelerometerReading;

    private static float[] rotationMatrix = new float[9];
    private static float[] orientationAngles = new float[3];

    public static void startSensors(Context context){
        magnetometerReading=null;
        accelerometerReading=null;
        new Magnetometer(context);
        new Accelerometer(context);
    }

    public static void writeResults(float[] source,boolean isAccelerometer){
        if(isAccelerometer){
            accelerometerReading = source;
        }
        else{
            magnetometerReading = source;
        }

        if(accelerometerReading==null || magnetometerReading==null){
            return;
        }
        android.hardware.SensorManager.getRotationMatrix(rotationMatrix, null,accelerometerReading, magnetometerReading);
        android.hardware.SensorManager.getOrientation(rotationMatrix, orientationAngles);

        PinActivity.displayValues((int)Math.round(orientationAngles[1]*(180/3.141)),(int)Math.round(orientationAngles[2]*(180/3.141)));
    }

}
