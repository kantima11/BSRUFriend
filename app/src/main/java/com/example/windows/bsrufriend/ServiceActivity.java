package com.example.windows.bsrufriend;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class ServiceActivity extends FragmentActivity implements OnMapReadyCallback {
    //explicit
    private GoogleMap mMap;
    private double userLatADouble = 13.733095, userLngADouble = 100.489422;
    private TextView textView;
    private Button button;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        //Bind Widget
        textView = (TextView)findViewById(R.id.textView2);
        button = (Button)findViewById(R.id.button4);

        //Recaive
        loginStrings = getIntent().getStringArrayExtra("Login");

        //Show text
        textView.setText(loginStrings[1]);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }   // Main Method

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setup Center of Map
        mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(userLatADouble,userLngADouble), 16));

    }   // onMapReady


}   //Main Class
