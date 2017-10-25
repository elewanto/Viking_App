package com.mygdx.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private TextView mXText;
    private TextView mYText;
    private TextView mZText;

    private Sensor mAccelerometer;
    private SensorManager mSensorManager;

    private static final String TAG = "AccelerometerActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        // create Sensor Manager
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // accelerometer sensor
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // register sensor listener
        // can also try DELAY_NORMAL or DELAY_FASTEST
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        mXText = (TextView) findViewById(R.id.x_text_view);
        mYText = (TextView) findViewById(R.id.y_text_view);
        mZText = (TextView) findViewById(R.id.z_text_view);
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    // accel values are stored in values[] array with x at index 0, y at index 1, z at index 2
    @Override
    public void onSensorChanged(SensorEvent event) {
        mXText.setText("X: " + event.values[0]);
        mYText.setText("Y: " + event.values[1]);
        mZText.setText("Z: " + event.values[2]);

    }

    // shouldn't need to use this method
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
