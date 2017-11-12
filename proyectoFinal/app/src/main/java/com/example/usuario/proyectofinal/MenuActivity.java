package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void flexionesPecho(View view) {
        Intent intent = new Intent(this, ContabilizadorActivity.class);
        startActivity(intent);
    }
    public void sentadillasPecho(View view) {
        Intent intent = new Intent(this, SentadillasActivity.class);
        startActivity(intent);
    }
}
