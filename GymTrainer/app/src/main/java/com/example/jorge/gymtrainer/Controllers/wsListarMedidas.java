package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jorge.gymtrainer.Activities.AdminActivities.ListadoUsuariosActivity;
import com.example.jorge.gymtrainer.Activities.UserActivities.MenuPrincipalActivity;
import com.example.jorge.gymtrainer.Modelos.Registro;
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
import java.util.ArrayList;

/**
 * Created by NIGGA on 14/11/2017.
 */

public class wsListarMedidas extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/medidas";
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    ListView listado;
    ArrayList<Registro> ListadoRegistros;


    public wsListarMedidas(Activity activity, ListView listado, ArrayList<Registro> ListadoRegistros, String UserID) {
        this.activity = activity;
        this.listado = listado;
        this.ListadoRegistros = ListadoRegistros;
        WebServiceURL = WebServiceURL+"/"+UserID;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected JSONObject doInBackground(String... args) {
        jsons = jParser.makeHttpRequest(WebServiceURL, "GET", null);
        Log.e("RESPUESTA--", jsons.toString());
        return jsons;
    }

    /**
     * Once the background process is done we need to Dismiss the progress dialog asap *
     **/

    protected void onPostExecute(JSONObject jsons) {
        super.onPostExecute(jsons);
        ArrayList<String> ListViewData = new ArrayList<>();
        try {
            JSONArray Array = jsons.getJSONArray("data");
            for (int i = 0; i < Array.length(); i++) {
                JSONObject RegistroOBJ = Array.getJSONObject(i);
                Registro RegistroTemporal = new Registro();
                RegistroTemporal.setID(RegistroOBJ.getString("id"));
                RegistroTemporal.setCreated_at(RegistroOBJ.getString("created_at"));
                RegistroTemporal.setGrasaCorporal(RegistroOBJ.getString("grasaCorporal"));
                RegistroTemporal.setRitmoCardiaco(RegistroOBJ.getString("ritmoCardiaco"));
                RegistroTemporal.setPeso(RegistroOBJ.getString("peso"));
                RegistroTemporal.setAltura(RegistroOBJ.getString("altura"));
                ListadoRegistros.add(RegistroTemporal);
                ListViewData.add("Medida: " + RegistroOBJ.getString("created_at"));
                Log.e("NOMBRE --- ", RegistroOBJ.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_list_item_1, ListViewData);
        listado.setAdapter(adapter);
    }//End onPostExecute
}