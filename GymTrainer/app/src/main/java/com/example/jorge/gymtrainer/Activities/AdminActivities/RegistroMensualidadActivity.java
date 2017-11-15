package com.example.jorge.gymtrainer.Activities.AdminActivities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Controllers.wsRegistrarMensualidad;
import com.example.jorge.gymtrainer.Controllers.wsSpinerRutinasPredeterminadas;
import com.example.jorge.gymtrainer.Controllers.wsSpinnerAlimentacionesPredeterminadas;
import com.example.jorge.gymtrainer.Modelos.Alimentaciones;
import com.example.jorge.gymtrainer.Modelos.Registro;
import com.example.jorge.gymtrainer.Modelos.Rutinas;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistroMensualidadActivity extends AppCompatActivity  implements View.OnTouchListener{

    EditText txtFechaRegistro, txtGrasaCorporal, txtRitmoCardiaco, txtPeso, txtAltura;
    Spinner spRutina, spAlimentacion;
    Usuario UsuarioRecibido;
    ArrayList<Rutinas> ListadoRutinas = new ArrayList<>();
    ArrayList<Alimentaciones> ListadoAlimentaciones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mensualidad);

        txtFechaRegistro = (EditText) findViewById(R.id.txtFechaRegistroMensualidad);
        txtGrasaCorporal = (EditText) findViewById(R.id.txtGrasaCorporalRegistroMensualidad);
        txtRitmoCardiaco = (EditText) findViewById(R.id.txtRitmoCardiacoRegistroMensualidad);
        txtPeso = (EditText) findViewById(R.id.txtPesoRegistroMensualidad);
        txtAltura = (EditText) findViewById(R.id.txtAlturaRegistroMensualidad);

        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("User");
        Toast.makeText(this, "ID: " + UsuarioRecibido.getID(), Toast.LENGTH_SHORT).show();


        spRutina = (Spinner) findViewById(R.id.spRutinaRegistroMensualidad);
        spAlimentacion = (Spinner) findViewById(R.id.spAlimentacionRegistroMensualidad);


        wsSpinerRutinasPredeterminadas Webservice = new wsSpinerRutinasPredeterminadas(this,spRutina, ListadoRutinas);
        Webservice.execute();

        wsSpinnerAlimentacionesPredeterminadas Webservice2 = new wsSpinnerAlimentacionesPredeterminadas(this,spAlimentacion, ListadoAlimentaciones);
        Webservice2.execute();
        txtFechaRegistro.setOnTouchListener(this);

        Limpiar();
    }

    public void Registrar(View v){

        String FechaRegistro = txtFechaRegistro.getText().toString();
        String GrasaCorporal = txtGrasaCorporal.getText().toString();
        String RitmoCardiaco = txtRitmoCardiaco.getText().toString();
        String Peso = txtPeso.getText().toString();
        String Altura = txtAltura.getText().toString();
        int RutinaSeleccionada = spRutina.getSelectedItemPosition();
        int AlimentacionSeleccionada = spAlimentacion.getSelectedItemPosition();
        if(FechaRegistro.equals("") || GrasaCorporal.equals("") || RitmoCardiaco.equals("")
            || Peso.equals("") || Altura.equals("") ){
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            Registro NuevoRegistro = new Registro();
            NuevoRegistro.setCreated_at(FechaRegistro);
            NuevoRegistro.setAlimentacion_ID(ListadoAlimentaciones.get(AlimentacionSeleccionada).getID());
            NuevoRegistro.setRutina_ID(ListadoRutinas.get(RutinaSeleccionada).getID());
            NuevoRegistro.setUsuario_ID(UsuarioRecibido.getID());
            NuevoRegistro.setGrasaCorporal(GrasaCorporal);
            NuevoRegistro.setRitmoCardiaco(RitmoCardiaco);
            NuevoRegistro.setPeso(Peso);
            NuevoRegistro.setAltura(Altura);
            wsRegistrarMensualidad Webservice = new wsRegistrarMensualidad(NuevoRegistro,this);
            Webservice.execute();
            Limpiar();
        }
    }

    public void Atras(View v){
        Intent intent = new Intent(this,InformacionUsuarioActivity.class);
        intent.putExtra("User", UsuarioRecibido);
        startActivity(intent);
        finish();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Calendar c = Calendar.getInstance();
            int cyear = c.get(Calendar.YEAR);
            int cmonth = c.get(Calendar.MONTH);
            int cday = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtFechaRegistro.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, cyear, cmonth, cday);
            datePickerDialog.show();
        }

        return true;
    }

    public void Limpiar(){
        txtFechaRegistro.setText("");
        txtAltura.setText("");
        txtGrasaCorporal.setText("");
        txtPeso.setText("");
        txtRitmoCardiaco.setText("");
        spRutina.setSelection(0);
        spAlimentacion.setSelection(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(this,InformacionUsuarioActivity.class);
            intent.putExtra("User", UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
