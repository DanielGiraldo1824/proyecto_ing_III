package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Controllers.wsListarNoticias;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

public class NoticiasActivity extends AppCompatActivity {

    ListView lvListadoNoticias;
    Usuario UsuarioRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        lvListadoNoticias = (ListView) findViewById(R.id.lvListadoNoticias);
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        wsListarNoticias Webservice = new wsListarNoticias(this, lvListadoNoticias);
        Webservice.execute();


        lvListadoNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getBaseContext(),"Posicion " + position, Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(NoticiasActivity.this,DescripcionAlimentacionActivity.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(NoticiasActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
