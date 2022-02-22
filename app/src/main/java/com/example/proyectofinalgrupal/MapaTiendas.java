package com.example.proyectofinalgrupal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ZoomControls;

import androidx.annotation.NonNull;
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

    ZoomControls zoomControls;
    Double longitude;
    Double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapatiendas);
        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        zoomControls = findViewById(R.id.zoom_controls);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //LocationManager es para sacar la Latitud y Longitud para poder luego utilizarla en el setMarcadorCoche y guardarlo en el sharedPreferences.
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        latitude = myLocation.getLatitude();
        longitude = myLocation.getLongitude();
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap mapa = googleMap;
        mapa.clear();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        UiSettings uiSettings = mapa.getUiSettings();
        mapa.setMyLocationEnabled(true);
        LatLng c = new LatLng(latitude, longitude);
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(c, 15f));

        zoomControls.setOnZoomInClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mapa.animateCamera(CameraUpdateFactory.zoomIn());
                    }
                }
        );
        zoomControls.setOnZoomOutClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mapa.animateCamera(CameraUpdateFactory.zoomOut());
                    }
                }
        );
    }
}
