package com.example.usuario.proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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

public class listaGymActivity extends AppCompatActivity {
    private String lenguajeProgramacion[];
    private String cedula;

    private Integer[] imgid={
            R.drawable.modelo,
            R.drawable.modelo,
            R.drawable.modelo,
            R.drawable.modelo,
            R.drawable.modelo
    };
    private ListView lista;

    //json con el cual visualizamos la respuesta del servicio
    public JSONArray obj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gym);
        cedula = (String) getIntent().getExtras().getSerializable("cedula");
        new ConsultGym().execute();


    }
    //hilo para consultar los gimansios registrados
    public class ConsultGym extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/consultarGym.php");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("tokens","lalapesca");

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
                    Log.e("SERVICIO", result);
                    obj = new JSONArray(result);
                    //identificamos si existe un usuario registrado con ese id y pass
                    lenguajeProgramacion = new String[obj.length()];
                    JSONObject nombre=obj.getJSONObject(0);
                    lenguajeProgramacion[0] = nombre.getString("name");
                    llenarLista();
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
    public void llenarLista(){
        LenguajeListAdapterArrendo adapter=new LenguajeListAdapterArrendo(this,lenguajeProgramacion,imgid);
        lista=(ListView)findViewById(R.id.mi_lista);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                Intent ListSong = new Intent(getApplicationContext(), DetalleGymActivity.class);

                JSONObject nombre= null;
                try {
                    nombre = obj.getJSONObject(0);
                    ListSong.putExtra("cedula", cedula);
                    ListSong.putExtra("id", nombre.getString("id"));
                    startActivity(ListSong);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), UsuarioActivity.class);
        intent.putExtra("cedula", cedula);
        startActivity(intent);
    }
}
