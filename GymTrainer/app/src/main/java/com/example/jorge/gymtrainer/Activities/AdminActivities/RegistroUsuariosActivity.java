package com.example.jorge.gymtrainer.Activities.AdminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Activities.LoginActivity;
import com.example.jorge.gymtrainer.Controllers.wsRegistrarUsuario;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;

public class RegistroUsuariosActivity extends AppCompatActivity {

    EditText txtCedula,txtNombre, txtApellidos, txtCorreoElectronico, txtContraseña, txtConfirmarContraseña;
    Spinner spTipoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtCorreoElectronico = (EditText) findViewById(R.id.txtCorreoElectronico);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        txtConfirmarContraseña = (EditText) findViewById(R.id.txtConfirmarContraseña);
        spTipoUsuario = (Spinner) findViewById(R.id.spTipoUsuario);

        ArrayList TiposDeUsuarios = new ArrayList();
        TiposDeUsuarios.add("Entrenador");
        TiposDeUsuarios.add("Deportista");
        ArrayAdapter TiposAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TiposDeUsuarios);
        spTipoUsuario.setAdapter(TiposAdapter);
        Limpiar();
    }

    public void Registrar(View v){
        String Cedula = txtCedula.getText().toString();
        String Nombres = txtNombre.getText().toString();
        String Apellidos = txtApellidos.getText().toString();
        String CorreoElectronico = txtCorreoElectronico.getText().toString();
        String Contraseña = txtContraseña.getText().toString();
        String ConfirmarContraseña = txtConfirmarContraseña.getText().toString();
        String TipoDeUsuario = spTipoUsuario.getSelectedItem().toString();
        if(txtCedula.getText().toString().equals("") || Nombres.equals("") || Apellidos.equals("") || CorreoElectronico.equals("")
                || Contraseña.equals("") || ConfirmarContraseña.equals("")){
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(Contraseña.equals(ConfirmarContraseña)){
                Usuario NuevoUsuario = new Usuario();
                NuevoUsuario.setCedula(Cedula);
                NuevoUsuario.setNombres(Nombres);
                NuevoUsuario.setApellidos(Apellidos);
                NuevoUsuario.setCorreoElectronico(CorreoElectronico);
                NuevoUsuario.setContraseña(Contraseña);
                NuevoUsuario.setTipoUsuario(TipoDeUsuario);
                wsRegistrarUsuario webService = new wsRegistrarUsuario(NuevoUsuario,this);
                webService.execute();
                Limpiar();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void irAtras(View v){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void Limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreoElectronico.setText("");
        txtContraseña.setText("");
        txtConfirmarContraseña.setText("");
    }
}
