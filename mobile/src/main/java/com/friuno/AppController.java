package com.friuno;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import okhttp3.OkHttpClient;

/**
 * Created by GodwinRoseSamuel on 20-11-2017.
 */

public class AppController extends Application implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = AppController.class.getSimpleName();
    private static AppController appController;
    private OkHttpClient okHttpClient;
    private GoogleApiHelper googleApiHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public static synchronized AppController getInstance() {
        return appController;
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }

    public GoogleApiHelper getGoogleApiHelper() {
        if (googleApiHelper == null) {
            googleApiHelper = new GoogleApiHelper(this);
        }
        return googleApiHelper;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}
