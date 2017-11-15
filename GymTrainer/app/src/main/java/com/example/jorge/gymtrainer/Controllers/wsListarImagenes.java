package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Gallery;

import com.example.jorge.gymtrainer.Modelos.GalleryAdapter;
import com.example.jorge.gymtrainer.Modelos.Reto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by NIGGA on 11/11/2017.
 */

public class wsListarImagenes extends AsyncTask<String, String, JSONObject> {
    String WebServiceURL;
    JSONParser jParser = new JSONParser();
    JSONObject jsons = new JSONObject();
    Activity activity;
    ArrayList<String> ListadoImagenes;
    Gallery gallery;
    ArrayList<Bitmap> ListadoBitmaps= new ArrayList<>();


    public wsListarImagenes(Activity activity, ArrayList<String> ListadoImagenes, int TipoImagenes, Gallery gallery){
        this.activity = activity;
        this.ListadoImagenes = ListadoImagenes;
        this.gallery = gallery;
        switch (TipoImagenes){
            case 1:
                WebServiceURL= "http://192.168.1.16/androidservicetaller/img/1";
                break;
            case 2:
                WebServiceURL= "http://192.168.1.16/androidservicetaller/img/2";
                break;
            case 3:
                WebServiceURL= "http://192.168.1.16/androidservicetaller/img/3";
                break;
        }
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
                String ImagenCondificada = Array.getString(i);
                final String encodedString = "data:image/jpg;base64, ....";
                final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
                final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                ListadoBitmaps.add(decodedByte);
                ListadoImagenes.add(ImagenCondificada);
                Log.e("IMAGEN --- ", ImagenCondificada);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }//End onPostExecute
}
