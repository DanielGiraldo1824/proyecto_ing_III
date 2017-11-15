package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jorge.gymtrainer.Controllers.wsListarImagenes;
import com.example.jorge.gymtrainer.Controllers.wsListarRetos;
import com.example.jorge.gymtrainer.Modelos.GalleryAdapter;
import com.example.jorge.gymtrainer.Modelos.Reto;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;
import java.util.Random;

public class RetosActivity extends AppCompatActivity implements SensorEventListener {

    TextView tvNombre, tvDescripcion;
    private static final float LIMITE_SENSIBILIDAD_SACUDIDA = 2.1f;
    private SensorManager sensorM;
    private Sensor sensorProximidad;
    private Sensor sensorAcelerometro;
    ImageView imagenSeleccionada;
    Gallery gallery;
    ArrayList<Reto> ListadoRetos = new ArrayList<>();
    ArrayList<String> ListadoImagenes = new ArrayList<>();
    final Integer[]  imagenes = { R.drawable.cardio,
            R.drawable.supercarga,
            R.drawable.rumbaterapia,
            R.drawable.abdominal,
            R.drawable.circuitopierna,
            R.drawable.circuitoespalda
    };
    Usuario UsuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retos);

        tvNombre = (TextView) findViewById(R.id.tvNombreRetos);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcionRetos);

        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");

        wsListarRetos WebserviceRetos = new wsListarRetos(this, ListadoRetos);
        WebserviceRetos.execute();
        //wsListarImagenes WebserviceImagenes = new wsListarImagenes(this, ListadoImagenes,1, gallery);
        //WebserviceImagenes.execute();

        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorProximidad = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorAcelerometro = sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorM.registerListener(RetosActivity.this, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(RetosActivity.this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);

        imagenSeleccionada = (ImageView) findViewById(R.id.ivRetoSeleccionada);



        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new GalleryAdapter(this, imagenes));
        gallery.setVisibility(View.INVISIBLE );
        //al seleccionar una imagen, la mostramos en el centro de la pantalla a mayor tamaño

        //con este listener, sólo se mostrarían las imágenes sobre las que se pulsa
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                imagenSeleccionada.setImageBitmap(decodeSampledBitmapFromResource(getResources(), imagenes[position], 300, 0));
            }

        });

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
                int Posicion = r.nextInt((ListadoRetos.size() - 1) + 1);
                Reto RetoSeleccionado = ListadoRetos.get(Posicion);
                tvNombre.setText("Nombre: " + RetoSeleccionado.getNombre());
                tvDescripcion.setText("Descripcion: " + RetoSeleccionado.getDescripcion());
                imagenSeleccionada.setImageBitmap(decodeSampledBitmapFromResource(getResources(), imagenes[Posicion], 300, 0));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(RetosActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
