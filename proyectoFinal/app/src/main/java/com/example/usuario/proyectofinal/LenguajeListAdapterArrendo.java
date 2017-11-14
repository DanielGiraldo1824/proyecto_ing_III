package com.example.usuario.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by FranciscoQuitian on 9/11/17.
 */

class LenguajeListAdapterArrendo extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] integers;

    public LenguajeListAdapterArrendo(Activity context, String[] itemname, Integer[] integers) {
        super(context, R.layout.fila_lista, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.integers=integers;
    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.fila_lista,null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        txtTitle.setText(itemname[posicion]);
        imageView.setImageResource(integers[posicion]);
        LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        imageView.setLayoutParams(layoutParams);
        etxDescripcion.setText("Mensualidd Gym : $1.200.000");

        return rowView;
    }


}
