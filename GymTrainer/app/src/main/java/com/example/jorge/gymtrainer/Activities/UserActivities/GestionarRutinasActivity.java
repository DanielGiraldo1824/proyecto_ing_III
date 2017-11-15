package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorge.gymtrainer.R;

import java.util.ArrayList;
import java.util.List;

public class GestionarRutinasActivity extends AppCompatActivity {

    Spinner spTipoRutina, spListadoRutinasPredeterminadas;
    EditText txtNombreRutina, txtDescripcionRutina;
    Button btnModificar, btnEliminar,btnGuardar,btnBuscar;
    TextView tvNombre,tvDescripcion, tvListadoRutinasPredeterminadas;
    int IdRutinaEncontrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_rutinas);

        txtNombreRutina = (EditText) findViewById(R.id.txtNombreRutinaGestionarRutina);
        txtDescripcionRutina = (EditText) findViewById(R.id.txtDescripcionGestionarRutinas);

        btnBuscar = (Button) findViewById(R.id.btnBuscarRutinaGestionarRutina);
        btnGuardar = (Button) findViewById(R.id.btnGuardarGestionarRutina);
        btnModificar = (Button) findViewById(R.id.btnModificarGestionarRutina);
        btnEliminar = (Button) findViewById(R.id.btnEliminarGestionarRutina);

        tvListadoRutinasPredeterminadas = (TextView) findViewById(R.id.tvListadoRutinasPredeterminadasGestionarRutinas);
        tvNombre = (TextView) findViewById(R.id.tvNombreRutinaGestionarRutina);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcionRutinaGestionarRutina);

        spTipoRutina = (Spinner) findViewById(R.id.spTipoRutinaGestionarRutina);
        spListadoRutinasPredeterminadas = (Spinner) findViewById(R.id.spListadoRutinasPredeterminadasGestionarRutinas);

        List<String> TipoRutina = new ArrayList<String>();
        TipoRutina.add("No seleccionada");
        TipoRutina.add("Personalizada");
        TipoRutina.add("Predeterminada");

        List<String> ListadoRutinasPredeterminadas = new ArrayList<String>();

        ArrayAdapter TipoRutinasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListadoRutinasPredeterminadas);
        ArrayAdapter RutinasPredeterminadasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TipoRutina);

        spTipoRutina.setAdapter(TipoRutinasAdapter);
        spListadoRutinasPredeterminadas.setAdapter(RutinasPredeterminadasAdapter);

        spTipoRutina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch (position){
                    case 0:
                        tvNombre.setVisibility(View.INVISIBLE);
                        tvDescripcion.setVisibility(View.INVISIBLE);
                        txtNombreRutina.setVisibility(View.INVISIBLE);
                        txtDescripcionRutina.setVisibility(View.INVISIBLE);
                        btnGuardar.setVisibility(View.INVISIBLE);
                        btnBuscar.setVisibility(View.INVISIBLE);
                        btnEliminar.setVisibility(View.INVISIBLE);
                        btnModificar.setVisibility(View.INVISIBLE);
                        spListadoRutinasPredeterminadas.setVisibility(View.INVISIBLE);
                        tvListadoRutinasPredeterminadas.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        tvNombre.setVisibility(View.VISIBLE);
                        tvDescripcion.setVisibility(View.VISIBLE);
                        txtNombreRutina.setVisibility(View.VISIBLE);
                        txtDescripcionRutina.setVisibility(View.VISIBLE);
                        btnGuardar.setVisibility(View.VISIBLE);
                        btnBuscar.setVisibility(View.VISIBLE);
                        btnEliminar.setVisibility(View.VISIBLE);
                        btnModificar.setVisibility(View.VISIBLE);
                        spListadoRutinasPredeterminadas.setVisibility(View.INVISIBLE);
                        tvListadoRutinasPredeterminadas.setVisibility(View.INVISIBLE);
                        Limpiar();
                        DeshabilitarEditarEliminar();
                        break;
                    case 2:
                        tvNombre.setVisibility(View.VISIBLE);
                        tvDescripcion.setVisibility(View.VISIBLE);
                        txtNombreRutina.setVisibility(View.VISIBLE);
                        txtDescripcionRutina.setVisibility(View.VISIBLE);
                        btnGuardar.setVisibility(View.VISIBLE);
                        btnBuscar.setVisibility(View.VISIBLE);
                        btnEliminar.setVisibility(View.VISIBLE);
                        btnModificar.setVisibility(View.VISIBLE);
                        spListadoRutinasPredeterminadas.setVisibility(View.VISIBLE);
                        tvListadoRutinasPredeterminadas.setVisibility(View.VISIBLE);
                        Limpiar();
                        DeshabilitarEditarEliminar();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        DeshabilitarEditarEliminar();
    }

    public void Buscar(View v){
        String Nombre = txtNombreRutina.getText().toString();
        if(Nombre.equals("")){
            Toast.makeText(this, "Por favor ingrese un nombre válido", Toast.LENGTH_SHORT).show();
        }else{
            //Codigo de Buscar
            IdRutinaEncontrada = 0;
            HabilitarEditarEliminar();
        }
    }

    public void Guardar(View v){
        String Nombre = txtNombreRutina.getText().toString();
        String Descripcion = txtDescripcionRutina.getText().toString();
        if(Nombre.equals("") || Descripcion.equals("")){
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            //Codigo de Guardar Rutina
            Toast.makeText(this, "Rutina Guardada Exitosamente", Toast.LENGTH_SHORT).show();
            Limpiar();
        }
    }

    public void Modificar(View v){
        String Nombre = txtNombreRutina.getText().toString();
        String Descripcion = txtDescripcionRutina.getText().toString();
        if(Nombre.equals("") || Descripcion.equals("")){
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            //Codigo de Modificar Rutina
            Toast.makeText(this, "Rutina Modificada Exitosamente", Toast.LENGTH_SHORT).show();
            Limpiar();
            DeshabilitarEditarEliminar();
        }
    }

    public void Eliminar(View v){
        //Codigo Eliminar Rutina
        DeshabilitarEditarEliminar();
    }

    public void Limpiar(){
        txtNombreRutina.setText("");
        txtDescripcionRutina.setText("");
    }

    public void HabilitarEditarEliminar(){
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }

    public void DeshabilitarEditarEliminar(){
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
}
