package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.view.View;
import android.graphics.Bitmap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.jorge.gymtrainer.Modelos.Alimentaciones;
import com.example.jorge.gymtrainer.Modelos.GalleryAdapter;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

public class DescripcionAlimentacionActivity extends AppCompatActivity {

    TextView tvNombre, tvDescripcion;
    Usuario UsuarioRecibido;
    Alimentaciones AlimentacionRecibida;
    ImageView imagenSeleccionada;
    Gallery gallery;
    final Integer[] imagenes = { R.drawable.frutas, R.drawable.verduras, R.drawable.complementos    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_alimentacion);
        tvNombre = (TextView) findViewById(R.id.tvNombreAlimentacion);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcionAlimentacion);
        imagenSeleccionada = (ImageView) findViewById(R.id.ivAlimentacionSeleccionada);

        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        AlimentacionRecibida = (Alimentaciones) getIntent().getSerializableExtra("alimentacion");
        tvNombre.setText("Nombre: " +  AlimentacionRecibida.getNombre());
        tvDescripcion.setText("Descripcion: " + AlimentacionRecibida.getDescripcion());

        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new GalleryAdapter(this, imagenes));
        //al seleccionar una imagen, la mostramos en el centro de la pantalla a mayor tamaño

        //con este listener, sólo se mostrarían las imágenes sobre las que se pulsa
        gallery.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                imagenSeleccionada.setImageBitmap(decodeSampledBitmapFromResource(getResources(), imagenes[position], 300, 0));
            }

        });
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
            Intent intent = new Intent(DescripcionAlimentacionActivity.this,ListadoAlimentacionesActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

