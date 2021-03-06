package com.jasonbutwell.accelerometerdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager sm;

    private TextView x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (TextView)findViewById(R.id.textViewX);
        y = (TextView)findViewById(R.id.textViewY);
        z = (TextView)findViewById(R.id.textViewZ);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onResume() {
        super.onResume();
        sm.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x.setText(String.valueOf(sensorEvent.values[0]));
        y.setText(String.valueOf(sensorEvent.values[1]));
        z.setText(String.valueOf(sensorEvent.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
