package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jorge.gymtrainer.Modelos.Alimentaciones;
import com.example.jorge.gymtrainer.Modelos.Registro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by NIGGA on 14/11/2017.
 */

public class wsListarAlimentaciones extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/alimentaciones";
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    ListView listado;
    ArrayList<Alimentaciones> ListadoAlimentaciones;


    public wsListarAlimentaciones(Activity activity, ListView listado, ArrayList<Alimentaciones> ListadoAlimentaciones) {
        this.activity = activity;
        this.listado = listado;
        this.ListadoAlimentaciones = ListadoAlimentaciones;
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
                JSONObject AlimentacionOBJ = Array.getJSONObject(i);
                Alimentaciones RegistroTemporal = new Alimentaciones();
                RegistroTemporal.setID(AlimentacionOBJ.getString("id"));
                RegistroTemporal.setNombre(AlimentacionOBJ.getString("nombre"));
                RegistroTemporal.setDescripcion(AlimentacionOBJ.getString("descripcion"));
                ListadoAlimentaciones.add(RegistroTemporal);
                ListViewData.add(AlimentacionOBJ.getString("nombre"));
                Log.e("NOMBRE --- ", AlimentacionOBJ.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_list_item_1, ListViewData);
        listado.setAdapter(adapter);
    }//End onPostExecute
}