package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Activities.AdminActivities.ListadoUsuariosActivity;
import com.example.jorge.gymtrainer.Modelos.Registro;
import com.example.jorge.gymtrainer.Modelos.Usuario;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NIGGA on 14/11/2017.
 */

public class wsRegistrarMensualidad extends AsyncTask<Void, String, Boolean> {

    private StringBuffer buffer = null;
    private Registro NuevoRegistro;
    private Activity activity;
    static InputStream is = null;
    static JSONObject jsonObj ;
    static String json = "";

    public wsRegistrarMensualidad(Registro registro, Activity activity) {
        this.NuevoRegistro = registro;
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            // I lofiu parce
//pendejo USTED NO PUEDE LLAMAR EL LOCALHOST POR QUE EL CELULAR NO ES LOCAL ES OTRO PC EN SI
            URL url = new URL("http://192.168.1.16/androidservicetaller/registrarMensualidad.php");


            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder().
                    appendQueryParameter("rutina",NuevoRegistro.getRutina_ID()).
                    appendQueryParameter("alimentacion", NuevoRegistro.getAlimentacion_ID()).
                    appendQueryParameter("user", NuevoRegistro.getUsuario_ID()).
                    appendQueryParameter("grasa", NuevoRegistro.getGrasaCorporal()).
                    appendQueryParameter("ritmo", NuevoRegistro.getRitmoCardiaco()).
                    appendQueryParameter("peso", NuevoRegistro.getPeso()).
                    appendQueryParameter("altura", NuevoRegistro.getAltura()).
                    appendQueryParameter("fecha", NuevoRegistro.getCreated_at());

            String query = builder.build().getEncodedQuery();
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(query);

            writer.flush();
            writer.close();
            os.close();

            publishProgress("Se envian los datos");
            conn.connect();

            InputStream stream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            buffer = new StringBuffer();

            String line;


            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

        } catch (MalformedURLException e) {
            publishProgress("Errr mal estructura URL " + e.getMessage());
            e.printStackTrace();
            Log.e("ERROR MALFORMED URL", e.getMessage());
            return false;
        } catch (IOException e) {
            publishProgress("Errr IO URL " + e.getMessage());
            Log.e("ERROR IOEXCEPTION", e.getMessage());
            Log.e("ERROR IOEXCEPTION", e.toString());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                publishProgress("Error al final");
                e.printStackTrace();
                e.printStackTrace();
            }
        }
        return true;

    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.e("RESPONSE", buffer.toString());
        if (result) {
            Toast.makeText(activity, "Medidas Registradas con Éxito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity,ListadoUsuariosActivity.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Error en la operacion", Toast.LENGTH_SHORT).show();
        }

    }
}
