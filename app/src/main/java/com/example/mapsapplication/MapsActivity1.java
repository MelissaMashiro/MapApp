package com.example.mapsapplication;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Camera;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity1 extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener {


    //IMPLEMENTÉ ONMARKERCLICKLISTERNER para poder trabajar con los marcadores(etiquetas de ubicacion)  y hacer que al hacer clik me muestre las coord

    private GoogleMap mMap;
    private Marker markerPrueba;

    double latitud_number;
    double longitud_number;
    double zoom_number;
    float color;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("lati");
            latitud_number = Double.valueOf(value);
            String value2 = extras.getString("longi");
            longitud_number = Double.valueOf(value2);
            String value3 = extras.getString("zoom");
            zoom_number = Double.valueOf(value3);
            String value4 = extras.getString("color");
            color=getColor(value4);
        }

        LatLng ubicacion = new LatLng(latitud_number, longitud_number);
        mMap.addMarker(new MarkerOptions().position(ubicacion).title("Ubicacion seleccionada").icon(BitmapDescriptorFactory.defaultMarker(color)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));


        //Camara focus y Zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, (float) zoom_number));

  /*      //Markador
        LatLng prueba= new LatLng(latitud_number,-longitud_number);
        markerPrueba = googleMap.addMarker(new MarkerOptions()
                    .position(prueba)
                    .title("prueba")
        );

        googleMap.setOnMarkerClickListener(this );*/
    }


    public float getColor(String value) {
        switch (value){
            case "HUE_CYAN":
                return BitmapDescriptorFactory.HUE_CYAN;
            case  "HUE_AZURE":
                return BitmapDescriptorFactory.HUE_AZURE;
            case "HUE_BLUE" :
                return BitmapDescriptorFactory.HUE_BLUE;
            case "HUE_RED":
                return BitmapDescriptorFactory.HUE_RED;
            case  "HUE_GREEN":
                return BitmapDescriptorFactory.HUE_GREEN;
            case "HUE_MAGNETA":
                return  BitmapDescriptorFactory.HUE_MAGENTA;
            default:
                return  BitmapDescriptorFactory.HUE_VIOLET;

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(markerPrueba)){
            String lat,lng;
            lat=Double.toString(marker.getPosition().latitude);
            lng=Double.toString(marker.getPosition().longitude);
            Toast.makeText(this,"Latitud: "+lat+" Longitud: "+lng,Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
