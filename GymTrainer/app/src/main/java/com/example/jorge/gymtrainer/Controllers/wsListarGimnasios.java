package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jorge.gymtrainer.Modelos.Gimnasio;
import com.example.jorge.gymtrainer.Modelos.Reto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIGGA on 13/11/2017.
 */

public class wsListarGimnasios extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/gimnasios";
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    ArrayList<Gimnasio> ListadoGimnasios;


    public wsListarGimnasios(Activity activity, ArrayList<Gimnasio> ListadoGimnasios){
        this.activity = activity;
        this.ListadoGimnasios = ListadoGimnasios;
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
                JSONObject GimnasioOBJ = Array.getJSONObject(i);
                Gimnasio GymTemporal = new Gimnasio();
                GymTemporal.setID(GimnasioOBJ.getString("id"));
                GymTemporal.setNombre(GimnasioOBJ.getString("nombre"));
                GymTemporal.setDescripcion(GimnasioOBJ.getString("descripcion"));
                GymTemporal.setLatitud(GimnasioOBJ.getString("latitud"));
                GymTemporal.setLongitud(GimnasioOBJ.getString("longitud"));

                ListadoGimnasios.add(GymTemporal);
                Log.e("NOMBRE --- ", GimnasioOBJ.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//End onPostExecute


}
