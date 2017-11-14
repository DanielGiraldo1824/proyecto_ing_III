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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class UsuarioActivity extends AppCompatActivity {
    String cedula,registroGym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        cedula = (String) getIntent().getExtras().getSerializable("cedula");
        new ConsultRegGym().execute();

    }

    //hilo para registrar un cliente
    public class ConsultRegGym extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                //URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/consultarRegGym.php");
                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/consultarRegGym.php");
                JSONObject postDataParams = new JSONObject();

                postDataParams.put("user", cedula);

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
            //json con el cual visualizamos la respuesta del servicio
            JSONObject obj = null;
            try {
                obj = new JSONObject(result);
                registroGym = obj.getString("total");
                //identificamos si existe un usuario registrado con ese id y pass
                if (registroGym.equals("0"))
                {
                    noGym("No te encuentras Registrado en ningun Gym","BIENVENIDO....");

                }else{

                }
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





    //cuando Identifica que el usuario no se encuentra registrado en ningun gimnasio
    public void noGym(String m, String titulo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UsuarioActivity.this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle(titulo);
        TextView myMsg = new TextView(UsuarioActivity.this);
        myMsg.setText("\n"+m+"\n ¿Desea registrarte? \n");
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        myMsg.setPadding(20, 0, 20, 0);
        alertDialog.setView(myMsg);
        alertDialog.setIcon(R.drawable.ic_launcher_background);
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent ListGym = new Intent(getApplicationContext(), listaGymActivity.class);
                        ListGym.putExtra("cedula", cedula);
                        startActivity(ListGym);

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

    //enviar al cliente a las opciones del contador
    public void rutinas(View view){
        if (registroGym.equals("0"))
        {
            noGym("para acceder a las rutinas debes de estar registrado","Mensaje del Sistema");

        }else{
            Intent intent = new Intent(getApplicationContext(), ConsltarFoodActivity.class);
            intent.putExtra("cedula", cedula);
            startActivity(intent);
        }
    }
    //enviar al cliente a las opciones del contador
    public void medidas(View view){
        if (registroGym.equals("0"))
        {
            noGym("para acceder a las medidas debes de estar registrado","Mensaje del Sistema");

        }else{
            Intent intent = new Intent(getApplicationContext(), ConsultarMedidaActivity.class);
            intent.putExtra("cedula", cedula);
            startActivity(intent);
        }
    }
    public void contabilizador(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

