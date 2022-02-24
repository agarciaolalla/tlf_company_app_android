package com.example.proyectofinalgrupal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaTiendas extends FragmentActivity implements OnMapReadyCallback {

    Double longitude;
    Double latitude;
    Button volver;
    String getMail;
    String getRol;
    String getPass;
    String getName;
    int numeroTiendas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapatiendas);

        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        volver = (Button) findViewById(R.id.volvermenu);
        getMail = getIntent().getStringExtra("mail");
        getPass = getIntent().getStringExtra("pass");
        getName = getIntent().getStringExtra("name");
        getRol = getIntent().getStringExtra("rol");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //LocationManager es para sacar la Latitud y Longitud para poder luego utilizarla en el setMarcadorCoche y guardarlo en el sharedPreferences.
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        latitude = myLocation.getLatitude();
        longitude = myLocation.getLongitude();
        //funcion para volver al menu principal
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapaTiendas.this, MenuPrincipal.class);
                i.putExtra("mail", getMail); //Te mete la variable del Mail para que en la otra clase la obtenga directamente
                i.putExtra("rol", getRol);
                i.putExtra("pass", getPass);
                i.putExtra("name", getName);
                startActivity(i);
            }
        });
    }
    public void salir(){
        Intent i = new Intent(MapaTiendas.this, MenuPrincipal.class);
        i.putExtra("mail", getMail); //Te mete la variable del Mail para que en la otra clase la obtenga directamente
        i.putExtra("rol", getRol);
        i.putExtra("pass", getPass);
        i.putExtra("name", getName);
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mapa = googleMap;
        mapa.clear();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        UiSettings uiSettings = mapa.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mapa.setMyLocationEnabled(true);
        LatLng c = new LatLng(latitude, longitude);

        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(c, 15f));


        LatLng tiendas[] = new LatLng[6];

        numeroTiendas = (int) Math.floor(Math.random()*5+1);

        for(int i = 0; i<=numeroTiendas; i++)
        {
            double lat,longi;
            lat= (Math.floor(Math.random()*999+1)) / 100000;
            longi  = ( Math.floor(Math.random()*999+1)) / 100000;

            tiendas[i] = new LatLng(latitude + lat, longitude + longi);
            mapa.addMarker(new MarkerOptions().position(tiendas[i]).title("Tienda de la compañia ").icon(BitmapDescriptorFactory.fromResource(R.drawable.imagentienda)));

        }


    }



}
