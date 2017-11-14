package com.example.usuario.proyectofinal;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
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

import static android.graphics.Color.parseColor;

public class FoodActivity extends AppCompatActivity {
    private ListView lv;
    private String tokens[];
    ArrayAdapter<String> adapter;
    public JSONArray obj = null;
    String cedula,gym,cliente;
    int dieta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        cliente = (String) getIntent().getExtras().getSerializable("cliente");
        gym = (String) getIntent().getExtras().getSerializable("gym");
        cedula = (String) getIntent().getExtras().getSerializable("cedula");
        new ConsultMedidas().execute();
    }

    //hilo para consultar los gimansios registrados
    public class ConsultMedidas extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/consultarFood.php");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("cedula",cedula);

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

            try {
                Log.e("SERVICIO", "hola"+result);
                obj = new JSONArray(result);
                //identificamos si existe un usuario registrado con ese id y pass
                tokens = new String[obj.length()];
                for (int i=0; i < obj.length();i++) {
                    JSONObject nombre = obj.getJSONObject(i);
                    tokens[i] = "Desayuno = "+nombre.getString("desayno")+"\n  Almuerzo = "+nombre.getString("almerzo")+"\n  Cena = "+nombre.getString("cena");
                }
                llenarCliente();
            } catch (JSONException e) {
                e.printStackTrace();
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

    public void llenarCliente(){
        lv = (ListView) findViewById(R.id.list_views);
        lv.setTextFilterEnabled(true);

        adapter = new ArrayAdapter<String>(this,R.layout.listmedidas, R.id.codigo_token,tokens);

        lv.setAdapter(adapter);


        // Evento para cuando doy click en algun elemento de la lista ( ListView )
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {


                dieta=position+1;
                noGym("Mensaje del sistema");
            }

        });
    }
    //cuando Identifica que el usuario no se encuentra registrado en ningun gimnasio
    public void noGym( String titulo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoodActivity.this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle(titulo);
        TextView myMsg = new TextView(FoodActivity.this);
        myMsg.setText("\n ¿Desea registrar la dieta al cliente? \n");
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        myMsg.setPadding(20, 0, 20, 0);
        alertDialog.setView(myMsg);
        alertDialog.setIcon(R.drawable.ic_launcher_background);
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new SendRegFood().execute();
                    }
                }
        );
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }

        );
        // alertDialog.show();
        AlertDialog a = alertDialog.create();
        cambiar_color_texto_alertdialog(a);
    }
    public void cambiar_color_texto_alertdialog(AlertDialog a) {
        a.show();
        Button BN = a.getButton(DialogInterface.BUTTON_NEGATIVE);
        BN.setTextColor(parseColor("#2E9AFE"));
        Button BA = a.getButton(DialogInterface.BUTTON_POSITIVE);
        BA.setTextColor(parseColor("#2E9AFE"));
    }
    //hilo para registrar medidas
    public class SendRegFood extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/registrarFood.php");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("dieta", dieta);
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
                Toast.makeText(getApplicationContext(), "Dieta Registrada Exitosamente",
                        Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Error registrando las dieta",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
