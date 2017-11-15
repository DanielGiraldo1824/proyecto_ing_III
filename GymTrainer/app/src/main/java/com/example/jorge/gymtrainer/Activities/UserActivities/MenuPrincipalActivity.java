package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Activities.AdminActivities.RegistroUsuariosActivity;
import com.example.jorge.gymtrainer.Activities.LoginActivity;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    Usuario UsuarioRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        //Toast.makeText(this, "ID: " + UsuarioRecibido.getID(), Toast.LENGTH_SHORT).show();
    }

    public void Contabilizador(View v){
        Intent intent = new Intent(this,ContabilizadorActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Rutinas(View v){
        Intent intent = new Intent(this,RutinasActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Medidas(View v){
        Intent intent = new Intent(this,ListadoMedidasActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Noticias(View v){
        Intent intent = new Intent(this,NoticiasActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Alimentacion(View v){
        Intent intent = new Intent(this,ListadoAlimentacionesActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Gimnasios(View v){
        Intent intent = new Intent(this,GimnasiosActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Retos(View v){
        Intent intent = new Intent(this,RetosActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Cronometro(View v){
        Intent intent = new Intent(this,CronometroActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void Reproductor(View v){
        Intent intent = new Intent(this,ReproductorActivity.class);
        intent.putExtra("user", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public void CerrarSesionMenu(View v){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cerrar Sesion")
                .setMessage("¿Está seguro que desea cerrar sesión?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MenuPrincipalActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Cerrar Sesion")
                    .setMessage("¿Está seguro que desea cerrar sesión?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MenuPrincipalActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        return super.onKeyDown(keyCode, event);
    }

}
