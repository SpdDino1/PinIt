package com.example.pinit.PinActivity.SensorManager.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.example.pinit.PinActivity.SensorManager.SensorManager;

public class Magnetometer  implements SensorEventListener {
    private android.hardware.SensorManager manager;

    public Magnetometer(Context context) {
        manager=(android.hardware.SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        manager.registerListener(
                this,
                manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                android.hardware.SensorManager.SENSOR_DELAY_FASTEST
        );
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        SensorManager.writeResults(sensorEvent.values,false);
        manager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
