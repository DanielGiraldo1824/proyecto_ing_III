package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuInstructorActivity extends AppCompatActivity {

    String cedula,gym,cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_instructor);
        cliente = (String) getIntent().getExtras().getSerializable("cliente");
        gym = (String) getIntent().getExtras().getSerializable("gym");
        cedula = (String) getIntent().getExtras().getSerializable("cedula");
    }

    public void ingresarMedidas(View view){
        Intent intent = new Intent(getApplicationContext(), IngresarMedidasActivity.class);
        intent.putExtra("cedula", cedula);
        intent.putExtra("gym", gym);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }

    public void alimentos(View view){
        Intent intent = new Intent(getApplicationContext(), MenuInstructorActivity.class);
        intent.putExtra("cedula", cedula);
        intent.putExtra("gym", gym);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }
}
