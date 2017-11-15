package com.example.jorge.gymtrainer.Activities.AdminActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Activities.LoginActivity;
import com.example.jorge.gymtrainer.Activities.UserActivities.MenuPrincipalActivity;
import com.example.jorge.gymtrainer.Controllers.wsListarUsuarios;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

public class ListadoUsuariosActivity extends AppCompatActivity {

    ListView lvListadoUsuarios;
    ArrayList<Usuario> ListadoUsuarios = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        lvListadoUsuarios = (ListView) findViewById(R.id.lvListadoUsuarios);


        wsListarUsuarios webservice = new wsListarUsuarios(this, lvListadoUsuarios, ListadoUsuarios);
        webservice.execute();


        lvListadoUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoUsuariosActivity.this,InformacionUsuarioActivity.class);
                intent.putExtra("User", ListadoUsuarios.get(position));
                startActivity(intent);
            }
        });
    }

    public void CerrarSesion(View v){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cerrar Sesion")
                .setMessage("¿Está seguro que desea cerrar sesión?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListadoUsuariosActivity.this,LoginActivity.class);
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
                            Intent intent = new Intent(ListadoUsuariosActivity.this,LoginActivity.class);
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
