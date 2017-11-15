package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

public class CronometroActivity extends AppCompatActivity {

    Button btnIniciar, btnPausar;
    Chronometer cronometro;
    long Time = 0;
    Usuario UsuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        btnIniciar = (Button) findViewById(R.id.btnIniciarCronometro);
        btnPausar = (Button) findViewById(R.id.btnPausarCronometro);
        cronometro = (Chronometer) findViewById(R.id.Cronometro);
        btnPausar.setEnabled(false);
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
       // cronometro.setFormat("H:MM:SS");

    }

    public void IniciarCronometro(View v){
        btnIniciar.setEnabled(false);
        btnPausar.setEnabled(true);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
    }

    public void PausarCronometro(View v){
        btnPausar.setEnabled(false);
        btnIniciar.setEnabled(true);
        cronometro.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(CronometroActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
