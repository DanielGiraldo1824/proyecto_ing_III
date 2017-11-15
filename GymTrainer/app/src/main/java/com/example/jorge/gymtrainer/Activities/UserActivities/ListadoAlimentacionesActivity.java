package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Controllers.wsListarAlimentaciones;
import com.example.jorge.gymtrainer.Modelos.Alimentaciones;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

public class ListadoAlimentacionesActivity extends AppCompatActivity {

    ListView lvListadoAlimentacion;
    Usuario UsuarioRecibido;
    ArrayList<Alimentaciones> ListadoAlimentaciones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_alimentaciones);
        lvListadoAlimentacion = (ListView) findViewById(R.id.lvListadoAlimentacion);
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        wsListarAlimentaciones Webservice = new wsListarAlimentaciones(this,lvListadoAlimentacion, ListadoAlimentaciones);
        Webservice.execute();

        lvListadoAlimentacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListadoAlimentacionesActivity.this,DescripcionAlimentacionActivity.class);
                intent.putExtra("alimentacion", ListadoAlimentaciones.get(position));
                intent.putExtra("user",UsuarioRecibido);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(ListadoAlimentacionesActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
