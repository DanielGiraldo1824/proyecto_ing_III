package com.example.jorge.gymtrainer.Activities.UserActivities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.jorge.gymtrainer.Controllers.wsListarGimnasios;
import com.example.jorge.gymtrainer.Modelos.Gimnasio;
import com.example.jorge.gymtrainer.Modelos.Usuario;
import com.example.jorge.gymtrainer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class GimnasiosActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final LatLng EAM = new LatLng(4.541763, -75.663463);
    private ArrayList<Gimnasio> ListadoGimnasios = new ArrayList<>();
    Usuario UsuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gimnasios);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        wsListarGimnasios Webservice = new wsListarGimnasios(this,ListadoGimnasios);
        Webservice.execute();
        UsuarioRecibido = (Usuario) getIntent().getSerializableExtra("user");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(EAM).title("EAM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EAM));

        for (int i = 0 ; i < ListadoGimnasios.size(); i++){
            LatLng TenpGym = new LatLng(Float.parseFloat(ListadoGimnasios.get(i).getLatitud()),
                                        Float.parseFloat(ListadoGimnasios.get(i).getLongitud()));
            mMap.addMarker(new MarkerOptions().position(TenpGym).title(ListadoGimnasios.get(i).getNombre()));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(GimnasiosActivity.this,MenuPrincipalActivity.class);
            intent.putExtra("user",UsuarioRecibido);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
