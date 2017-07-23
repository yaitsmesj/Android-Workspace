package com.example.suraj.locations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Location";
    LocationManager locationManager;
    LocationListener locationListener;
    public static final int LOCATION_REQ = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "Long : " + location.getLongitude());
                Log.d(TAG, "Latt : " + location.getLatitude());
                Log.d(TAG, "Prov : " + location.getProvider());
                Log.d(TAG, "Acc : " + location.getAccuracy());
                Log.d(TAG, "Alt : " + location.getAltitude());
                Log.d(TAG, "Speed : " + location.getSpeed());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
                &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQ);

        }else
        {
            startLocationTracking();
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permission,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permission,grantResults);

        if(permission[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION)){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                startLocationTracking();
            else
                finish();
        }
    }

    @SuppressWarnings("MissingPermission")
    public void startLocationTracking(){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                10000,
                10,
                locationListener);
    }

}