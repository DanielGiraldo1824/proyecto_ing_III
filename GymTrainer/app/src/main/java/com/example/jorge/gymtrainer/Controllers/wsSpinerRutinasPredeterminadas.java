package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.jorge.gymtrainer.Modelos.Rutinas;
import com.example.jorge.gymtrainer.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIGGA on 11/11/2017.
 */

public class wsSpinerRutinasPredeterminadas extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL = "http://192.168.1.16/androidservicetaller/rutinas";
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    Spinner spRutinas;
    ArrayList<Rutinas> ListadoRutinas;


    public wsSpinerRutinasPredeterminadas(Activity activity, Spinner spRutinas, ArrayList<Rutinas> ListadoRutinas) {
        this.activity = activity;
        this.spRutinas = spRutinas;
        this.ListadoRutinas = ListadoRutinas;
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
        List<String> ListadoRutinas = new ArrayList<String>();
        try {
            JSONArray Array = jsons.getJSONArray("data");
            for (int i = 0; i < Array.length(); i++) {
                JSONObject RutinaOBJ = Array.getJSONObject(i);

                Rutinas RutinaTemporal = new Rutinas();
                RutinaTemporal.setID(RutinaOBJ.getString("id"));
                RutinaTemporal.setNombre(RutinaOBJ.getString("nombre"));
                RutinaTemporal.setDescripcion(RutinaOBJ.getString("descripcion"));
                RutinaTemporal.setTipoRutina(RutinaOBJ.getString("tiporutina"));
                ;
                this.ListadoRutinas.add(RutinaTemporal);
                ListadoRutinas.add(RutinaOBJ.getString("nombre"));
                Log.e("RUTINA PREDETERMINADA", RutinaOBJ.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter RutinasAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, ListadoRutinas);

        spRutinas.setAdapter(RutinasAdapter);
    }//End onPostExecute
}