package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Controllers.wsListarMedidas;
import com.example.jorge.gymtrainer.Modelos.Registro;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

public class ListadoMedidasActivity extends AppCompatActivity {

    ListView lvListadoMedidas;
    Usuario UsuarioRecibido;
    ArrayList<Registro> ListadoRegistros = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidas);
        lvListadoMedidas = (ListView) findViewById(R.id.lvListadoMedidas);
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");


        wsListarMedidas webservice = new wsListarMedidas(this,lvListadoMedidas,ListadoRegistros, UsuarioRecibido.getID());
        webservice.execute();

        lvListadoMedidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListadoMedidasActivity.this,DetalleMedidaActivity.class);
                intent.putExtra("registro", ListadoRegistros.get(position));
                intent.putExtra("user", UsuarioRecibido);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(ListadoMedidasActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user", UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
