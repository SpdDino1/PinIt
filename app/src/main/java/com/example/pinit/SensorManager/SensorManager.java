package com.example.pinit.SensorManager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorManager implements SensorEventListener{
    private float[] magnetometerReading;
    private float[] accelerometerReading;

    private float[] rotationMatrix = new float[9];
    private float[] orientationAngles = new float[3];

    private android.hardware.SensorManager manager;

    private boolean isUnregisterListener;
    private SensorAngleResult sensorAngleResult;

    public SensorManager(Context context,boolean isUnregisterListener,SensorAngleResult sensorAngleResult) {
        this.manager = (android.hardware.SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.isUnregisterListener=isUnregisterListener;
        this.sensorAngleResult=sensorAngleResult;

        manager.registerListener(
                this,
                manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                android.hardware.SensorManager.SENSOR_DELAY_FASTEST
        );
        manager.registerListener(
                this,
                manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                android.hardware.SensorManager.SENSOR_DELAY_FASTEST
        );
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelerometerReading = sensorEvent.values;
        }
        else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerReading = sensorEvent.values;
        }

        if(accelerometerReading==null || magnetometerReading==null){
            return;
        }

        if(isUnregisterListener) {
            manager.unregisterListener(this);
        }
        android.hardware.SensorManager.getRotationMatrix(rotationMatrix, null,accelerometerReading, magnetometerReading);
        android.hardware.SensorManager.getOrientation(rotationMatrix, orientationAngles);

        sensorAngleResult.passResult((int)Math.round(orientationAngles[0]*(180/3.141)),(int)Math.round(orientationAngles[2]*(180/3.141)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
