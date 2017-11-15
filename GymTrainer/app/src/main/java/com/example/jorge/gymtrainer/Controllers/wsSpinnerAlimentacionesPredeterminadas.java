package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jorge.gymtrainer.Modelos.Alimentaciones;
import com.example.jorge.gymtrainer.Modelos.Rutinas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIGGA on 11/11/2017.
 */

public class wsSpinnerAlimentacionesPredeterminadas extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/alimentaciones";

    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    Spinner spAlimentaciones;
    ArrayList<Alimentaciones> ListadoAlimentaciones;


    public wsSpinnerAlimentacionesPredeterminadas(Activity activity, Spinner spAlimentaciones, ArrayList<Alimentaciones> ListadoAlimentaciones) {
        this.activity = activity;
        this.spAlimentaciones = spAlimentaciones;
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
        List<String> ListadoAlimentacionesArray = new ArrayList<String>();
        try {
            JSONArray Array = jsons.getJSONArray("data");
            for (int i = 0; i < Array.length(); i++) {
                JSONObject AlimentacionOBJ = Array.getJSONObject(i);

                Alimentaciones AlimentacionTemporal = new Alimentaciones();
                AlimentacionTemporal.setID(AlimentacionOBJ.getString("id"));
                AlimentacionTemporal.setNombre(AlimentacionOBJ.getString("nombre"));
                AlimentacionTemporal.setDescripcion(AlimentacionOBJ.getString("descripcion"));
                ;
                this.ListadoAlimentaciones.add(AlimentacionTemporal);
                ListadoAlimentacionesArray.add(AlimentacionOBJ.getString("nombre"));
                Log.e("ALIMENTACION PREDETE", AlimentacionOBJ.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter RutinasAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, ListadoAlimentacionesArray);

        spAlimentaciones.setAdapter(RutinasAdapter);
    }//End onPostExecute
}