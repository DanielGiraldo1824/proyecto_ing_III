package com.example.usuario.proyectofinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RegistrarActivity extends AppCompatActivity {
    private EditText cedula,nombre,telefono,pass,idGym;
    private Spinner tipoUser;
    //el URlL del archivo del servidor donde se envia la imagen
    private static final String UPLOAD_URL = "http://192.168.1.111/prueba/proyecto_ing_III/services/registrarUsuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        cedula = (EditText) findViewById(R.id.idRegistroCedula);
        nombre = (EditText) findViewById(R.id.idRegistroNombre);
        telefono = (EditText) findViewById(R.id.idRegistroTelefono);
        pass = (EditText) findViewById(R.id.idRegistroContrase);
        idGym = (EditText) findViewById(R.id.editText);
        //llenar spinner
        tipoUser = (Spinner) findViewById(R.id.idTipoClient);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.tpoClient , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoUser.setAdapter(spinner_adapter);
        tipoUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                Log.e("Registro",selectedItem);
                if(id == 1){
                    idGym.setVisibility(view.VISIBLE);
                }else{
                    idGym.setVisibility(view.INVISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }
    //metodo que lleva al activity Login
    public void cancelarRegistro(View view){
        finish();
    }

    //evento para registrar el cliente
    public void registrarCliente(View view)
    {
        new SendReg().execute();
    }

    //hilo para registrar un cliente
    public class SendReg extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/registrarUsuario.php");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("tipoClient", tipoUser.getSelectedItemId());
                postDataParams.put("cedula", cedula.getText().toString());
                postDataParams.put("nombre", nombre.getText().toString());
                postDataParams.put("telefono", telefono.getText().toString());
                postDataParams.put("pass", pass.getText().toString());
                postDataParams.put("gym",idGym.getText().toString());

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("Registro", result);
            if (result.equals("true"))
            {
                Toast.makeText(getApplicationContext(), "Usuario Registrado Exitosamente",
                        Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Cedula ingresada, ya se encuentra registrada",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
