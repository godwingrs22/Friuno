package com.friuno.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.friuno.R;
import com.friuno.util.InternetResolver;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by GodwinRoseSamuel on 20-07-2016.
 */
public class LocationFragment extends Fragment implements LocationListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private static final String PREF_FILE_NAME = "shared_pref";
    private static final String LATITUDE = "latitude";
    private static final String LONGTITUDE = "longtitude";
    private View rootView;
    private MapView mapView;
    public GoogleMap googleMap;
    public static double longtitude, latitude;
    public LocationManager locationManager;
    public LocationListener locationListener;
    public String provider;
    private Marker mylocationmarker;
    private Circle mylocationcircle;
    static LatLng mycoordinate;
    boolean camerapos = false;
    Location location;
    private int key;
    private ArrayList<Double> latlnglist;
    private Button locationButton, internetButton;
    private Boolean locationChange = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_location, container, false);
        if (savedInstanceState == null) {
            switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
                case ConnectionResult.SUCCESS:
                    mapView = (MapView) rootView.findViewById(R.id.locationMap);
                    mapView.onCreate(savedInstanceState);

                    if (mapView != null) {
                        if (googleMap == null) {
                            MapsInitializer.initialize(this.getActivity());
                        }
                        googleMap = mapView.getMap();
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
                    }
                    break;
                case ConnectionResult.SERVICE_MISSING:
                    Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                    break;
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                    Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
            }

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            locationListener = this;
            locationManager = (LocationManager) rootView.getContext().getSystemService(rootView.getContext().LOCATION_SERVICE);

            locationButton = (Button) rootView.findViewById(R.id.locationButton);
            internetButton = (Button) rootView.findViewById(R.id.internetbutton);

            locationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });

            internetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                }
            });

            new locationManagerThread().execute();
        }
        return rootView;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(preferenceName);
        editor.putString(preferenceName, preferenceValue);
        editor.commit();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }


    @Override
    public void onMapClick(LatLng latLng) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
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
        mycoordinate = dragPosition;
        mylocationcircle.setCenter(mycoordinate);
        saveToPreferences(rootView.getContext(), LATITUDE, String.valueOf(dragLat));
        saveToPreferences(rootView.getContext(), LONGTITUDE, String.valueOf(dragLong));
        Log.i("info", "on drag end :" + dragLat + " dragLong :" + dragLong);
    }

    private class locationManagerThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        if (getActivity() == null)
                            return;
                        locationManagerHandler.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 102:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationHandler();
                } else {
                    Toast.makeText(getContext(), "Location Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void locationHandler() {
        try {
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, true);
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 102);
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 0, locationListener);

            if (location != null) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()), 10);
                googleMap.animateCamera(cameraUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Handler locationManagerHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            locationHandler();
//                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
//                }
        }
    };


    @Override
    public void onLocationChanged(Location location) {
        Log.d("map", "<-----" + location.getLatitude() + "---" + location.getLongitude());
        if (!locationChange) {
            this.location = location;
            mycoordinate = new LatLng(location.getLatitude(), location.getLongitude());
            locationChange = true;
        }
        myLocationMarkerHandler.sendEmptyMessage(0);
        cameraPositionHandler.sendEmptyMessage(0);
    }

    private Handler cameraPositionHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (!camerapos) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(mycoordinate).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    camerapos = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Handler myLocationMarkerHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (mylocationmarker == null) {
                    mylocationmarker = googleMap.addMarker(new MarkerOptions().position(mycoordinate).draggable(true)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    mylocationmarker.showInfoWindow();
                    mylocationcircle = googleMap.addCircle(new CircleOptions()
                            .center(mycoordinate)
                            .radius(100)
                            .strokeColor(R.color.md_deep_purple_500)
                            .fillColor(0x40ff0000));
                    saveToPreferences(rootView.getContext(), LATITUDE, String.valueOf(mycoordinate.latitude));
                    saveToPreferences(rootView.getContext(), LONGTITUDE, String.valueOf(mycoordinate.longitude));

                } else {
                    mylocationcircle.setCenter(mycoordinate);
                    mylocationmarker.setPosition(mycoordinate);
                    mylocationmarker.showInfoWindow();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onResume() {
        super.onResume();
        new resumeHandlerAsync().execute();
    }

    private Handler resumeHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                mapView.onResume();

                String isLocationEnabled = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
                if (isLocationEnabled == null || isLocationEnabled.equals("")) {
                    locationButton.setVisibility(View.VISIBLE);
                    locationButton.setText("Friuno needs to access your Location.Please turn on your location");
                } else {
                    locationButton.setVisibility(View.INVISIBLE);
                }

                boolean isInternetEnabled = InternetResolver.isInternetAvailable(getActivity());
                if (!isInternetEnabled) {
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setText("Unable to load Google map.Please check your internet connections");
                } else {
                    internetButton.setVisibility(View.INVISIBLE);
                }

                if (mycoordinate == null) {
                    mycoordinate = new LatLng(Double.valueOf(readFromPreferences(rootView.getContext(), LATITUDE, "0")), Double.valueOf(readFromPreferences(rootView.getContext(), LONGTITUDE, "0")));
                    myLocationMarkerHandler.sendEmptyMessage(0);
                    cameraPositionHandler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private class resumeHandlerAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        if (getActivity() == null)
                            return;
                        resumeHandler.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mapView.onPause();
            mycoordinate = null;
            camerapos = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
