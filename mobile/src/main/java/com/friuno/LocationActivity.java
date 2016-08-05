package com.friuno;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends AppCompatActivity implements GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {

    private Toolbar toolbar;
    private GoogleMap googleMap;
    private final int RQS_GooglePlayServices = 1;
    private Location myLocation;
    boolean markerClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>FRIUNO</font>"));

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        LocationActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentById(R.id.locationMap);
        final SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;
        googleMap = supportmapfragment.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerDragListener(this);

        markerClicked = false;
//        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.locationMap);
//        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode == ConnectionResult.SUCCESS) {
            Toast.makeText(getApplicationContext(), "isGooglePlayServicesAvailable SUCCESS", Toast.LENGTH_LONG).show();
        } else {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        markerClicked = false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        googleMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        markerClicked = false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng dragPosition = marker.getPosition();
        double dragLat = dragPosition.latitude;
        double dragLong = dragPosition.longitude;
        Toast.makeText(getApplicationContext(), String.valueOf(dragLat) + "---" + String.valueOf(dragLong), Toast.LENGTH_LONG).show();
    }
}
