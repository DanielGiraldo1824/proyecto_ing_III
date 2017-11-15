package com.example.jorge.gymtrainer.Controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ClsCronometro extends AsyncTask<Void,Integer,Boolean> {

     TextView minutos,segundos;
    private Activity activity;
    int cont=0;

    public ClsCronometro(Activity activity, TextView minutos, TextView segundos){
        this.activity=activity;
        this.minutos=minutos;
        this.segundos=segundos;
    }


    public void limpiar(){minutos.setText("");segundos.setText("");}

    private void detenerHilo(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
    }

    @Override
    protected Boolean doInBackground(Void... values) {
        for(int i=5;i>=0;i--){
            for(int j=60;j>=0;j--){
                publishProgress(i,j);
                detenerHilo();
            if(isCancelled()){
                break;
            }
            }
        }
        return true;
    }

    @Override
    protected  void onProgressUpdate(Integer... values){
        int progreso=values[0].intValue();
        int progreso1=values[1].intValue();
        Log.e("prueba",progreso+"");
        Log.e("prueba",progreso1+"");
        minutos.setText(""+progreso+"");
        segundos.setText(""+progreso1+"");
    }
    @Override
    protected  void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(activity, "LA BOMBA HA SIDO DESACTIVADA",
                    Toast.LENGTH_SHORT).show();

        }
    }
        @Override
        protected  void onCancelled(){

                Toast.makeText(activity, "BOOOOM !!!!",
                        Toast.LENGTH_SHORT).show();


    }
}
