package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ReproductorActivity extends AppCompatActivity implements SensorEventListener {

    ListView lvListadoCanciones;
    private ArrayList<String> ListadoCanciones,TitulosCanciones;
    private MediaPlayer player;
    private SensorManager sensorM;
    private Sensor sensorProximidad;
    private Sensor sensorAcelerometro;
    private static final float LIMITE_SENSIBILIDAD_SACUDIDA = 2.1f;
    private double volumen=  0.5;
    private int PosicionActual = 0;
    Usuario UsuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        lvListadoCanciones = (ListView) findViewById(R.id.lvListadoCanciones);

        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");

        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorProximidad = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorAcelerometro = sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        player = new MediaPlayer();

        sensorM.registerListener(ReproductorActivity.this, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(ReproductorActivity.this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);


        ListadoCanciones = new ArrayList<String>();
        TitulosCanciones = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, TitulosCanciones);
        lvListadoCanciones.setAdapter(adapter);

        getAllSongs();
        lvListadoCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PosicionActual = i;
                PlaySong(ListadoCanciones.get(i));
            }


        });
    }

    public void PausarReproductor(View v){
        if (player.isPlaying()) {
            player.pause();
        }
    }

    private void PlaySong(String Path){
        if(player.isPlaying()){
            player.stop();
        }
        player.reset();

        try {
            player.setDataSource(Path);
            player.prepare();
            player.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllSongs(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if(songCursor != null && songCursor.moveToFirst()) {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songPath = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                String currentTitle = songCursor.getString(songTitle);
                String currentPath = songCursor.getString(songPath);
                TitulosCanciones.add(currentTitle);
                ListadoCanciones.add(currentPath);

            } while(songCursor.moveToNext());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            /*Actualice los datos del acelerometro*/

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            /*Se calcula con la operacion matematica el cambio gravitacional*/
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            //Si supera el limite establecido, suena el audio
            if (gForce > LIMITE_SENSIBILIDAD_SACUDIDA) {

                Random r = new Random();
                PosicionActual = r.nextInt((ListadoCanciones.size() - 1) + 1);
                PlaySong(ListadoCanciones.get(PosicionActual));
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
            Intent intent = new Intent(ReproductorActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
