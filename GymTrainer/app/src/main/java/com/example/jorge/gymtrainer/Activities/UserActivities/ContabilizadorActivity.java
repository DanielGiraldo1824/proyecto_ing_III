package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.Random;

public class ContabilizadorActivity extends AppCompatActivity implements SensorEventListener {

    int TotalSentadillas = 0, TotalLagartijas = 0;
    TextView tvTotalSentadillas, tvTotalLagartijas;
    private SensorManager sensorM;
    private Sensor sensorProximidad;
    private Sensor sensorAcelerometro;
    private static final float LIMITE_SENSIBILIDAD_SACUDIDA = 2.1f;
    Usuario UsuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contabilizador);
        tvTotalLagartijas = (TextView) findViewById(R.id.tvTotalLagartijasContabilizador);
        tvTotalSentadillas = (TextView) findViewById(R.id.tvTotalSentadillasContabilizador);

        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorProximidad = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorAcelerometro = sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorM.registerListener(ContabilizadorActivity.this, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(ContabilizadorActivity.this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void ReiniciarLagartijas(View v){
        TotalLagartijas = 0;
        tvTotalLagartijas.setText("0");
    }

    public void ReiniciarSentadillas(View v){
        TotalSentadillas = 0;
        tvTotalSentadillas.setText("0");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            float valor = Float.parseFloat(String.valueOf(event.values[0]));
            if (valor == 0) {
                TotalLagartijas++;
                tvTotalLagartijas.setText(""+TotalLagartijas);
            }
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            /*Actualice los datos del acelerometro*/

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            /*Se calcula con la operacion matematica el cambio gravitacional*/
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            //Si supera el limite establecido, suena el audio
            if (gForce > LIMITE_SENSIBILIDAD_SACUDIDA) {

                TotalSentadillas++;
                tvTotalSentadillas.setText(""+TotalSentadillas);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(ContabilizadorActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

