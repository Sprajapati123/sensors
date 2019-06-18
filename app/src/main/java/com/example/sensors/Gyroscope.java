package com.example.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Gyroscope extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView tvgryo;
    private EditText first,second;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setTitle("Gyroscope sensor");
        tvgryo=findViewById(R.id.tvgyro);
        first=findViewById(R.id.etfirstnumber);
        second=findViewById(R.id.etsecondnumber);
        calculate=findViewById(R.id.btncalculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }


        });

        sensorGyro();
    }

    private void sensorGyro(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1]<0){
                    tvgryo.setText("left");
                    add();
                }else if (event.values[1]>0){
                    tvgryo.setText("Right");
                    subtract();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor!=null){
            sensorManager.registerListener(gyroListener,sensor,sensorManager.SENSOR_DELAY_NORMAL);
        }else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }
    private void add() {
        int sum = Integer.parseInt(first.getText().toString())+Integer.parseInt(second.getText().toString());
        Toast.makeText(Gyroscope.this, "Sum is " +sum, Toast.LENGTH_SHORT).show();
    }

    private void subtract() {
        int result = Integer.parseInt(first.getText().toString())-Integer.parseInt(second.getText().toString());
        Toast.makeText(Gyroscope.this, "The result is " +result, Toast.LENGTH_SHORT).show();
    }
}
