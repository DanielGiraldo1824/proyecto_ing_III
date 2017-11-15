package com.example.jorge.gymtrainer.Activities.AdminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

public class InformacionUsuarioActivity extends AppCompatActivity {

    TextView tvNombreUsuario, tvCedula, tvCorreoElectronico;
    Usuario UsuarioRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_usuario);

        tvNombreUsuario = (TextView) findViewById(R.id.tvNombreInformacionUsuario);
        tvCedula = (TextView) findViewById(R.id.tvCedulaInformacionUsuario);
        tvCorreoElectronico = (TextView) findViewById(R.id.tvCorreoElectronicoInformacionUsuario);

        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("User");

        tvNombreUsuario.setText("Nombre: " + UsuarioRecibido.getNombres() + " " + UsuarioRecibido.getApellidos());
        tvCedula.setText("Cedula: " + UsuarioRecibido.getCedula());
        tvCorreoElectronico.setText("Correo Electrónico: " + UsuarioRecibido.getCorreoElectronico());

    }

    public void RegistrarMensualidad(View v){
        Intent intent = new Intent(this,RegistroMensualidadActivity.class);
        intent.putExtra("User", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Atras(View v){
        Intent intent = new Intent(this,ListadoUsuariosActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(this,ListadoUsuariosActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
