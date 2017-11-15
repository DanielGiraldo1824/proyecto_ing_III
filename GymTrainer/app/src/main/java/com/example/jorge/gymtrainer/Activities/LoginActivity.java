package com.example.jorge.gymtrainer.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Activities.AdminActivities.ListadoUsuariosActivity;
import com.example.jorge.gymtrainer.Activities.AdminActivities.RegistroUsuariosActivity;
import com.example.jorge.gymtrainer.Activities.UserActivities.MenuPrincipalActivity;
import com.example.jorge.gymtrainer.Controllers.wsLogin;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = (EditText) findViewById(R.id.txtUsuarioLogin);
        txtContraseña = (EditText) findViewById(R.id.txtContraseñaLogin);
        Limpiar();
    }

    public void IniciarSesion(View v){
        String Usuario = txtUsuario.getText().toString();
        String Contraseña = txtContraseña.getText().toString();
        if(Usuario.equals("") || Contraseña.equals("")){
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            /*
            com.example.jorge.gymtrainer.Modelos.Usuario LoginUser = new Usuario();
            LoginUser.setCedula(Usuario);
            LoginUser.setContraseña(Contraseña);
            wsLogin WebserviceLogin = new wsLogin(LoginUser,this);
            WebserviceLogin.execute();*/

            //Codigo quemado para mostrar los sensores
            Usuario UsuarioLoged = new Usuario();
            UsuarioLoged.setID("777");
            UsuarioLoged.setNombres("777");
            UsuarioLoged.setApellidos("777");
            UsuarioLoged.setCorreoElectronico("777");
            UsuarioLoged.setContraseña("777");
            UsuarioLoged.setTipoUsuario("777");
            Toast.makeText(this, "Bienvenid@ " + UsuarioLoged.getNombres() +" "+ UsuarioLoged.getApellidos(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MenuPrincipalActivity.class);
            intent.putExtra("user", UsuarioLoged);
            startActivity(intent);
            finish();
        }
    }

    public void irRegistrarse(View v){
        Intent intent = new Intent(this,RegistroUsuariosActivity.class);
        startActivity(intent);
        finish();
    }

    public void Limpiar(){
        txtUsuario.setText("");
        txtContraseña.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Salir")
                    .setMessage("¿Está seguro que desea salir de la aplicación?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
