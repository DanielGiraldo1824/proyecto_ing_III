package com.example.usuario.proyectofinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    private EditText user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.loginUser);
        pass = (EditText)findViewById(R.id.loginPass);
    }
    //metodo que lleva al activity registrar
    public void registrar(View view){
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }
    //Metodos de logue
    public void Login(View view){

        new ConsultLog().execute();
    }
    //hilo para registrar un cliente
    public class ConsultLog extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/consultarLogin.php");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("user", user.getText().toString());
                postDataParams.put("pass", pass.getText().toString());

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
                    //identificamos si existe un usuario registrado con ese id y pass
                    if (obj.getString("total").equals("1"))
                    {
                        String nombre=obj.getString("name");
                        if(obj.getString("tipo").equals("0")){

                            Toast.makeText(getApplicationContext(), "Bienvenido "+nombre+", Buena suerte con la rutina",
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), UsuarioActivity.class);
                            intent.putExtra("cedula", user.getText().toString());
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(), "Bienvenido Instructor "+nombre,
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                            intent.putExtra("cedula", user.getText().toString());
                            startActivity(intent);
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "usuario o contraseña incorrectos",
                                Toast.LENGTH_LONG).show();
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
}
