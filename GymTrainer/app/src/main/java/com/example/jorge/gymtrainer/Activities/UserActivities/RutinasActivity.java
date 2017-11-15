package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Activities.AdminActivities.InformacionUsuarioActivity;
import com.example.jorge.gymtrainer.Activities.AdminActivities.ListadoUsuariosActivity;
import com.example.jorge.gymtrainer.Modelos.Rutinas;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

public class RutinasActivity extends AppCompatActivity {

    ListView lvRutinasAsignadas, lvRutinasPersonalizadas;
    ArrayList<String> ListadoRutinasAsignadas,ListadoRutinasPersonalizadas;
    Usuario UsuarioRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
        lvRutinasAsignadas = (ListView) findViewById(R.id.lvListadoRutinasAsignadasRutinas);
        lvRutinasPersonalizadas = (ListView) findViewById(R.id.lvRutinasPersonalizadasRutinas);

        ListadoRutinasAsignadas = new ArrayList<>();
        ListadoRutinasAsignadas.add("Rutina1");
        ListadoRutinasPersonalizadas = new ArrayList<>();
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        ArrayAdapter<String> RutinasAsignadasAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, ListadoRutinasAsignadas);

        ArrayAdapter<String> RutinasPersonalizadasAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, ListadoRutinasPersonalizadas);

        lvRutinasAsignadas.setAdapter(RutinasAsignadasAdapter);
        lvRutinasPersonalizadas.setAdapter(RutinasPersonalizadasAdapter);

        lvRutinasAsignadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RutinasActivity.this,DescripcionRutinaActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        lvRutinasPersonalizadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RutinasActivity.this,DescripcionRutinaActivity.class);
                startActivity(intent);
                //finish();
            }
        });

    }

    public void GestionarRutinas(View v){
        Intent intent = new Intent(RutinasActivity.this,GestionarRutinasActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(RutinasActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
