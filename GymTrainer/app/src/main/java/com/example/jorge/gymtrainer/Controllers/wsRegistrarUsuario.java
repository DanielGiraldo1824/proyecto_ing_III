package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Modelos.Usuario;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nigga on 18/09/2017.
 */
public class wsRegistrarUsuario extends AsyncTask<Void, String, Boolean> {

    private StringBuffer buffer = null;
    private Usuario NuevoUsuario;
    private Activity activity;
    static InputStream is = null;
    static JSONObject jsonObj ;
    static String json = "";

    public wsRegistrarUsuario(Usuario usuario, Activity activity) {
        this.NuevoUsuario = usuario;
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
            URL url = new URL("http://192.168.1.16/androidservicetaller/registrarUsuario.php");


            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder().
                    appendQueryParameter("cedula",NuevoUsuario.getCedula()).
                    appendQueryParameter("nombre", NuevoUsuario.getNombres()).
                    appendQueryParameter("apellido", NuevoUsuario.getApellidos()).
                    appendQueryParameter("email", NuevoUsuario.getCorreoElectronico()).
                    appendQueryParameter("tipousuario", NuevoUsuario.getTipoUsuario()).
                    appendQueryParameter("contraseña", NuevoUsuario.getContraseña());

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
        if (result) {
            Toast.makeText(activity.getApplicationContext(), "Operacion exitosa ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Error en la operacion", Toast.LENGTH_SHORT).show();
        }

    }
}
