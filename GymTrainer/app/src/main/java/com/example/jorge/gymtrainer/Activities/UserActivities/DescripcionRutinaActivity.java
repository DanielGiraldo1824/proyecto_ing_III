package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.jorge.gymtrainer.Modelos.GalleryAdapter;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

import static android.R.attr.version;

public class DescripcionRutinaActivity extends AppCompatActivity {



    TextView tvNombreRutina, tvDescripcionRutina;
    ImageView imagenSeleccionada;
    Gallery gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_rutina);
        tvNombreRutina = (TextView) findViewById(R.id.tvNombreRutinaDescripcion);
        tvDescripcionRutina = (TextView) findViewById(R.id.tvDescripcionRutinaDescripcion);
        imagenSeleccionada = (ImageView) findViewById(R.id.ivRutinaSeleccionada);

        final Integer[] imagenes = { R.drawable.a, R.drawable.b, R.drawable.c,
                R.drawable.d, R.drawable.e,R.drawable.f,R.drawable.g
        };

        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new GalleryAdapter(this, imagenes));
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



}
