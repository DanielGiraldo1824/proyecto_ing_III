package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Modelos.Reto;
import com.example.jorge.gymtrainer.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by NIGGA on 11/11/2017.
 */

public class wsListarRetos extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/retos";
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    ArrayList<Reto> ListadoRetos;


    public wsListarRetos(Activity activity, ArrayList<Reto> ListadoRetos){
        this.activity = activity;
        this.ListadoRetos = ListadoRetos;
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
        try {
            JSONArray Array = jsons.getJSONArray("data");
            for(int i = 0; i< Array.length(); i++){
                JSONObject RetoOBJ = Array.getJSONObject(i);
                Reto RetoTemporal = new Reto();
                RetoTemporal.setID(RetoOBJ.getString("id"));
                RetoTemporal.setNombre(RetoOBJ.getString("nombre"));
                RetoTemporal.setDescripcion(RetoOBJ.getString("descripcion"));
                ListadoRetos.add(RetoTemporal);
                Log.e("NOMBRE --- ", RetoOBJ.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//End onPostExecute


}
