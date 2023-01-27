package com.example.proximitysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager !=null){
            Sensor proxy = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if(proxy !=null){
                sensorManager.registerListener(this, proxy,SensorManager.SENSOR_DELAY_FASTEST );
            }

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            ((TextView)findViewById(R.id.txtvalue)).setText("values" +event.values[0]);

            if (event.values[0]>0){
                Toast.makeText(this, "Object is Far", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Object is near", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}