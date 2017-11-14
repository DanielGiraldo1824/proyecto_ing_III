package com.example.usuario.proyectofinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class IngresarMedidasActivity extends AppCompatActivity {

    EditText grasa,altura,ritmo,fecha;
    String cedula,gym,cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_medidas);

        grasa = (EditText)findViewById(R.id.etGrasa);
        altura = (EditText)findViewById(R.id.etAltura);
        ritmo = (EditText)findViewById(R.id.etRitmo);
        fecha = (EditText)findViewById(R.id.etFecha);

        cliente = (String) getIntent().getExtras().getSerializable("cliente");
        gym = (String) getIntent().getExtras().getSerializable("gym");
        cedula = (String) getIntent().getExtras().getSerializable("cedula");
    }

    public void registrarMedidas(View view){
        new SendRegMedidas().execute();
    }

    //hilo para registrar medidas
    public class SendRegMedidas extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.10/Universidad/proyecto_ing_III/services/registrarMedidas.php");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("grasa", grasa.getText().toString());
                postDataParams.put("altura", altura.getText().toString());
                postDataParams.put("ritmo", ritmo.getText().toString());
                postDataParams.put("fecha", fecha.getText().toString());
                postDataParams.put("gym", gym);
                postDataParams.put("instructor", cedula);
                postDataParams.put("cliente", cliente);

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
                Toast.makeText(getApplicationContext(), "Medidas Registrado Exitosamente",
                        Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Error registrando las medidas",
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
