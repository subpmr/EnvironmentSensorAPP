package com.example.shubham.environmentsensorapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final String TAG = "GraphSensors";
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextView XA, YA, ZA, XG, YG, ZG, XM, YM, ZM, Temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAccel();
        //startTemp();
        //startGyro();
        //startMag();


    }

    public void initializeViews() {
        XA = (TextView) findViewById(R.id.XA);
        YA = (TextView) findViewById(R.id.YA);
        ZA = (TextView) findViewById(R.id.ZA);
        XG = (TextView) findViewById(R.id.XA);
        YG = (TextView) findViewById(R.id.YA);
        ZG = (TextView) findViewById(R.id.ZA);
        XM = (TextView) findViewById(R.id.XM);
        YM = (TextView) findViewById(R.id.YM);
        ZM = (TextView) findViewById(R.id.ZM);
        Temp = (TextView) findViewById(R.id.ZA);

    }

    public void startGyro(){
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void startAccel(){
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }



    public void startTemp(){
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void startMag(){
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void NextActivity(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            XG.setText("" + event.values);


        } else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            XA.setText("" + event.values[0]);
            YA.setText("" + event.values[1]);
            ZA.setText("" + event.values[2]);

        } else if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            Temp.setText("" + event.values);

        } else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            XM.setText("" + event.values[0]);
            YM.setText("" + event.values[1]);
            ZM.setText("" + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}