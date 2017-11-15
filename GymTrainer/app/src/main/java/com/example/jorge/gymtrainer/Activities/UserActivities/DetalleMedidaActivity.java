package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.jorge.gymtrainer.Modelos.Registro;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

public class DetalleMedidaActivity extends AppCompatActivity {
    TextView tvFecha, tvGrasaCorporal, tvAltura, tvRitmoCardiaco;
    Usuario UsuarioRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medida);
        tvFecha = (TextView) findViewById(R.id.tvFechaMedidas);
        tvGrasaCorporal = (TextView) findViewById(R.id.tvGrasaCorporalMedidas);
        tvAltura = (TextView) findViewById(R.id.tvAlturaMedidas);
        tvRitmoCardiaco = (TextView) findViewById(R.id.tvRitmoCardiacoMedidas);
        Registro RegistroRecibido = (Registro) getIntent().getSerializableExtra("registro");
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
        tvFecha.setText("Fecha Medida: " + RegistroRecibido.getCreated_at());
        tvGrasaCorporal.setText("Grasa Corporal: " + RegistroRecibido.getGrasaCorporal());
        tvAltura.setText("Altura: " + RegistroRecibido.getAltura());
        tvRitmoCardiaco.setText("Ritmo Cardiaco: " + RegistroRecibido.getRitmoCardiaco());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(DetalleMedidaActivity.this,ListadoMedidasActivity.class);
            intent.putExtra("user", UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
