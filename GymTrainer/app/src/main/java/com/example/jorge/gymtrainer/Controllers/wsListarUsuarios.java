package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIGGA on 11/11/2017.
 */

public class wsListarUsuarios extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/user";
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    ListView listado;
    ArrayList<Usuario> ListadoUsuarios;


    public wsListarUsuarios(Activity activity, ListView listado, ArrayList<Usuario> ListadoUsuarios){
        this.activity = activity;
        this.listado = listado;
        this.ListadoUsuarios = ListadoUsuarios;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected JSONObject doInBackground(String... args) {
        jsons = jParser.makeHttpRequest(WebServiceURL, "GET",null);
        Log.e("RESPUESTA--", jsons.toString());
        return jsons;
    }
    /** * Once the background process is done we need to Dismiss the progress dialog asap * **/

    protected void onPostExecute(JSONObject jsons) {
        super.onPostExecute(jsons);
        ArrayList<String> ListViewData = new ArrayList<>();
        try {
            JSONArray Array = jsons.getJSONArray("data");
            for(int i = 0; i< Array.length(); i++){
                JSONObject UsuarioOBJ = Array.getJSONObject(i);
                Usuario UsuarioTemporal = new Usuario();
                UsuarioTemporal.setID(UsuarioOBJ.getString("id"));
                UsuarioTemporal.setCedula(UsuarioOBJ.getString("cedula"));
                UsuarioTemporal.setNombres(UsuarioOBJ.getString("name"));
                UsuarioTemporal.setApellidos(UsuarioOBJ.getString("lastname"));
                UsuarioTemporal.setCorreoElectronico(UsuarioOBJ.getString("email"));
                UsuarioTemporal.setContraseña(UsuarioOBJ.getString("password"));
                ListadoUsuarios.add(UsuarioTemporal);
                ListViewData.add("Cedula: " + UsuarioOBJ.getString("cedula") + " \nNombre:" + UsuarioOBJ.getString("name"));
                Log.e("NOMBRE --- ", UsuarioOBJ.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_list_item_1, ListViewData);
           listado.setAdapter(adapter);
    }//End onPostExecute


}
