package com.example.shubham.environmentsensorapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

//FragmentActivity

public class GraphActivity extends Activity implements SensorEventListener {



    private final String TAG = "GraphSensors";
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private GraphView mGraphGyro;
    private GraphView mGraphAccel;
    private GraphView mGraphGravity;
    private GraphView mGraphTemp;
    private GraphView mGraphMag;

    private LineGraphSeries<DataPoint> mSeriesGyroX, mSeriesGyroY, mSeriesGyroZ;
    private LineGraphSeries<DataPoint> mSeriesAccelX, mSeriesAccelY, mSeriesAccelZ;
    private LineGraphSeries<DataPoint> mSeriesGravX, mSeriesGravY, mSeriesGravZ;
    private LineGraphSeries<DataPoint> mSeriesMagX, mSeriesMagY, mSeriesMagZ;
    private LineGraphSeries<DataPoint> mSeriesTemp;

    private double graphLastGyroXValue = 5d;
    private double graphLastAccelXValue = 5d;
    private double graphLastGravXValue = 5d;
    private double graphLastTempXValue = 5d;
    private double graphLastMagXValue = 5d;


    private TextView XA, YA, ZA,XG, YG, ZG,XM, YM, ZM, Temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_main);

        mGraphGyro = initGraph(R.id.graph1, "Sensor.TYPE_PROXIMOTY");
        mGraphAccel = initGraph(R.id.graph2, "Sensor.TYPE_ACCELEROMETER");
        //mGraphGravity = initGraph(R.id.graphGravity, "Sensor.TYPE_GRAVITY");
        mGraphTemp = initGraph(R.id.graph3, "Sensor.TYPE_AMBIENT_TEMPERATURE");
        mGraphMag = initGraph(R.id.graph4, "Sensor.TYPE_MAGNETIC_FIELD");



        // ACCEL
        mSeriesAccelX = initSeries(Color.BLUE, "X");
        mSeriesAccelY = initSeries(Color.RED, "Y");
        mSeriesAccelZ = initSeries(Color.GREEN, "Z");

        mGraphAccel.addSeries(mSeriesAccelX);
        mGraphAccel.addSeries(mSeriesAccelY);
        mGraphAccel.addSeries(mSeriesAccelZ);


        //GYRO
        mSeriesGyroX = initSeries(Color.BLUE, "Prox");
        mGraphGyro.addSeries(mSeriesGyroX);

        startGyro();

        // ACCEL
        mSeriesAccelX = initSeries(Color.BLUE, "X");
        mSeriesAccelY = initSeries(Color.RED, "Y");
        mSeriesAccelZ = initSeries(Color.GREEN, "Z");

        mGraphAccel.addSeries(mSeriesAccelX);
        mGraphAccel.addSeries(mSeriesAccelY);
        mGraphAccel.addSeries(mSeriesAccelZ);

        startAccel();


        // Temp
        mSeriesTemp = initSeries(Color.RED, "Temp");
        mGraphTemp.addSeries(mSeriesTemp);

        startTemp();

        // Magnatic
        mSeriesMagX = initSeries(Color.BLUE, "X");
        mSeriesMagY = initSeries(Color.RED, "Y");
        mSeriesMagZ = initSeries(Color.GREEN, "Z");

        mGraphMag.addSeries(mSeriesMagX);
        mGraphMag.addSeries(mSeriesMagY);
        mGraphMag.addSeries(mSeriesMagZ);

        startMag();

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


    public GraphView initGraph(int id, String title) {
        GraphView graph = (GraphView) findViewById(id);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);
        graph.getGridLabelRenderer().setLabelVerticalWidth(100);
        graph.setTitle(title);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        return graph;
    }

    public LineGraphSeries<DataPoint> initSeries(int color, String title) {
        LineGraphSeries<DataPoint> series;
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(true);
        series.setDrawBackground(false);
        series.setColor(color);
        series.setTitle(title);
        return series;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            graphLastGyroXValue += 0.15d;
            mSeriesGyroX.appendData(new DataPoint(graphLastGyroXValue, event.values[0]), true, 33);
            mSeriesGyroY.appendData(new DataPoint(graphLastGyroXValue, event.values[1]), true, 33);
            mSeriesGyroZ.appendData(new DataPoint(graphLastGyroXValue, event.values[2]), true, 33);
        } else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            graphLastAccelXValue += 0.15d;
            mSeriesAccelX.appendData(new DataPoint(graphLastAccelXValue, event.values[0]), true, 33);
            mSeriesAccelY.appendData(new DataPoint(graphLastAccelXValue, event.values[1]), true, 33);
            mSeriesAccelZ.appendData(new DataPoint(graphLastAccelXValue, event.values[2]), true, 33);
        } else if(event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            graphLastGravXValue += 0.15d;
            mSeriesGravX.appendData(new DataPoint(graphLastGravXValue, event.values[0]), true, 33);
            mSeriesGravY.appendData(new DataPoint(graphLastGravXValue, event.values[1]), true, 33);
            mSeriesGravZ.appendData(new DataPoint(graphLastGravXValue, event.values[2]), true, 33);
        } else if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            graphLastTempXValue += 0.15d;
            mSeriesTemp.appendData(new DataPoint(graphLastTempXValue, event.values[0]), true, 33);
        } else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            graphLastMagXValue += 0.15d;
            mSeriesMagX.appendData(new DataPoint(graphLastMagXValue, event.values[0]), true, 33);
            mSeriesMagY.appendData(new DataPoint(graphLastMagXValue, event.values[1]), true, 33);
            mSeriesMagZ.appendData(new DataPoint(graphLastMagXValue, event.values[2]), true, 33);
        }
        String dataString = String.valueOf(event.accuracy) + "," + String.valueOf(event.timestamp) + "," + String.valueOf(event.sensor.getType()) + "\n";
        Log.d(TAG, "Data received: " + dataString);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged called for the gyro");
    }
}


