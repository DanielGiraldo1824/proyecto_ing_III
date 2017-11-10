package com.example.usuario.proyectofinal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class SentadillasActivity extends AppCompatActivity implements SensorEventListener {

    int sentadillas = 0 ;
    private TextView conteo;
    private SensorManager sensorManager;
    private Sensor sensorAcelerometro;

    private static final int LIMITE_TIEMPO_MS_ROTACION = 1000;
    private long tiempoAcelerometro = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentadillas);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        conteo = (TextView) findViewById(R.id.tvContadorSentadilla);
        //Acelerometro
        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( SentadillasActivity.this,sensorAcelerometro,SensorManager.SENSOR_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        /*Se obtuvo datos del acelerometro?*/
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            /*Actualice los datos del acelerometro*/

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            /*Se captura el tiempo actual*/
            long now = System.currentTimeMillis();
            /*Si esta rotacion supera el tiempo estipulado para la proxima rotacion*/
            if ((now - tiempoAcelerometro) > LIMITE_TIEMPO_MS_ROTACION) {
            /*Se almacena para ser utilizado en una proxima rotacion*/
                tiempoAcelerometro = now;
                if (event.values[1] < 2)
                {
                    sentadillas++;
                    conteo.setText("" + sentadillas);
                }
            /*Se determina si se tuvo una sacudida (Con otro componente que es la gravedad)
            detectShake(event);*/
            }
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
