package com.example.usuario.proyectofinal;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ContabilizadorActivity extends AppCompatActivity implements SensorEventListener{

    private TextView contador;
    private SensorManager sensorM;
    private Sensor s;
    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contabilizador);
        contador=(TextView)findViewById(R.id.tvContador);

        sensorM=(SensorManager)getSystemService(SENSOR_SERVICE);

        s=sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorM.registerListener(ContabilizadorActivity.this,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor=Float.parseFloat(String.valueOf(event.values[0]));
        contador.setText(""+total);

            if (valor == 0) {
                total++;
            } else {

            }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
