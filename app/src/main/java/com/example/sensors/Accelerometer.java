package com.example.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Accelerometer extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView showaxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
    setTitle("Accelerometer sensor");

    showaxis=findViewById(R.id.tvshowaxis);
    sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;

                String xAxis = "x : " + values[0];
                String yAxis = "y : " + values[1];
                String zAxis = "z : " + values[2];

                showaxis.setText(xAxis + ""+ yAxis +"" + zAxis);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor!=null){
            sensorManager.registerListener(sel,sensor,sensorManager.SENSOR_DELAY_NORMAL);
        }else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }

    }
}
