package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Activities.AdminActivities.ListadoUsuariosActivity;
import com.example.jorge.gymtrainer.Activities.AdminActivities.RegistroUsuariosActivity;
import com.example.jorge.gymtrainer.Activities.UserActivities.MenuPrincipalActivity;
import com.example.jorge.gymtrainer.Modelos.Reto;
import com.example.jorge.gymtrainer.Modelos.Usuario;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NIGGA on 13/11/2017.
 */

public class wsLogin extends AsyncTask<Void, String, Boolean> {

    private StringBuffer buffer = null;
    private Usuario NuevoUsuario;
    private Activity activity;

    public wsLogin(Usuario usuario, Activity activity) {
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
            URL url = new URL("http://192.168.1.16/androidservicetaller/consultarLogin.php");


            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder().
                    appendQueryParameter("usuario",NuevoUsuario.getCedula()).
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
        if(result){
            String respuesta = buffer.toString();
            Log.e("Respuesta", respuesta);
            if(respuesta.equals("{\"data\":null}")){
                Toast.makeText(activity, "Usuario no encontrado / Datos incorrectos", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    JSONObject jsonObj = new JSONObject(respuesta);
                    JSONObject User = jsonObj.getJSONObject("data");
                    Usuario UsuarioLoged = new Usuario();
                    UsuarioLoged.setID(User.getString("id"));
                    UsuarioLoged.setNombres(User.getString("name"));
                    UsuarioLoged.setApellidos(User.getString("lastname"));
                    UsuarioLoged.setCorreoElectronico(User.getString("email"));
                    UsuarioLoged.setContraseña(User.getString("password"));
                    UsuarioLoged.setTipoUsuario(User.getString("tipoUsuario"));
                    Toast.makeText(activity, "Bienvenid@ " + UsuarioLoged.getNombres() +" "+ UsuarioLoged.getApellidos(), Toast.LENGTH_SHORT).show();
                    if(UsuarioLoged.getTipoUsuario().equals("Deportista")){
                        Intent intent = new Intent(activity,MenuPrincipalActivity.class);
                        intent.putExtra("user", UsuarioLoged);
                        activity.startActivity(intent);
                        activity.finish();
                    }else{
                        Intent intent = new Intent(activity,ListadoUsuariosActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                } catch (JSONException e) {
                    Log.e("Error json", e.getMessage());
                }
            }
        }else{

        }

    }
}
