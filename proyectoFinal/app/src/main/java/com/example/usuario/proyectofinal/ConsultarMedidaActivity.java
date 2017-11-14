package com.example.usuario.proyectofinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ConsultarMedidaActivity extends AppCompatActivity {
    private ListView lv;
    private String cedula;
    private String tokens[];
    ArrayAdapter<String> adapter;
    public JSONArray obj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_medida);
        cedula = (String) getIntent().getExtras().getSerializable("cedula");
        new ConsultMedidas().execute();
    }
    //hilo para consultar los gimansios registrados
    public class ConsultMedidas extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://192.168.1.111/prueba/proyecto_ing_III/services/consultarMedidas.php");

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
                Log.e("SERVICIO", result);
                obj = new JSONArray(result);
                //identificamos si existe un usuario registrado con ese id y pass
                tokens = new String[obj.length()];
                for (int i=0; i < obj.length();i++) {
                    JSONObject nombre = obj.getJSONObject(i);
                    tokens[i] = "Grasa Corporal = "+nombre.getString("grasaCorporal")+"\n Fecha Registro = "+nombre.getString("fechaRegistro");
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
                Toast toast1 = Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER,0 ,0 );

                toast1.show();
            }

        });



    }
}
