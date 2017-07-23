package com.example.suraj.googlemaps;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Path;
import android.location.Location;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.suraj.googlemaps.R.string.google_maps_key;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{


    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    public static final String TAG = "Location";
    private static final int FINE_LOCATION_REQ_CODE = 123;
    private static final int DEFAULT_ZOOM = 15;
    private static final String DEBUG_TAG = "LifeCycle";
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private boolean mLocationPermissionGranted = false;
    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;
    private LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private int mMaxEntries =5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;

    int PLACE_PICKER_REQUEST = 1;

    ArrayList<PolylineOptions> polyLineOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        addPlaceAutoCompleteFragment();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.current_place_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.option_get_place){
       //     showCurrentPlace();
        //    setPlacePicker();
            getDirections();
            Log.d(TAG, "onOptionsItemSelected: ");
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle outState = new Bundle();
        outState = getIntent().getExtras();
        if(outState!=null){
            mCameraPosition = outState.getParcelable(KEY_CAMERA_POSITION);
            mLastKnownLocation = outState.getParcelable(KEY_LOCATION);
        }

    }

    @Override
    protected void onStop() {
        Bundle outState = new Bundle();
        outState.putParcelable(KEY_LOCATION,mLastKnownLocation);
        outState.putParcelable(KEY_CAMERA_POSITION,mMap.getCameraPosition());
        getIntent().putExtras(outState);
        super.onStop();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custrom_info_content, null);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });


        if (ContextCompat.checkSelfPermission(this.getApplicationContext()
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, FINE_LOCATION_REQ_CODE);
        }

        updateLocationUI();
        getDeviceLocation();
     //   setPlacePicker();

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @SuppressWarnings("MissingPermission")
    private void getDeviceLocation(){

        if(mLocationPermissionGranted){
            mLastKnownLocation = LocationServices.FusedLocationApi
                                    .getLastLocation(mGoogleApiClient);
            Log.d(TAG, "getDeviceLocation: "+mLastKnownLocation.getLatitude()+" "+mLastKnownLocation.getLongitude());

        }
        
        if(mCameraPosition != null){
            Log.d(TAG, "getDeviceLocation: Camera Position");
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        }else if(mLastKnownLocation != null){
            Log.d(TAG, "getDeviceLocation: Last Known Location");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude()),DEFAULT_ZOOM
            ));
        }else{
            Log.d(TAG, "getDeviceLocation: Current Location is null. Using Defaults");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation,DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

    }


    @SuppressWarnings("MissingPermission")
    public void updateLocationUI(){
        if(mMap==null)
            return;

        if(mLocationPermissionGranted){
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }else{
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

     //   Log.d(TAG, "onRequestPermissionsResult: ");
        if(permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                Log.d(TAG, "onRequestPermissionsResult: ");
                updateLocationUI();
                getDeviceLocation();
            }
        }

    }


    @SuppressWarnings("MissingPermission")
    public void showCurrentPlace(){
        if(mMap==null)
            return;

        if(mLocationPermissionGranted){

            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(
                                                                mGoogleApiClient,null
            );

            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                    int i =0;

                    mLikelyPlaceNames = new String[mMaxEntries];
                    mLikelyPlaceAddresses = new String[mMaxEntries];
                    mLikelyPlaceAttributions = new String[mMaxEntries];
                    mLikelyPlaceLatLngs = new LatLng[mMaxEntries];

                    for(PlaceLikelihood place: placeLikelihoods){
                        mLikelyPlaceNames[i] = (String)place.getPlace().getName();
                        mLikelyPlaceAddresses[i] = (String)place.getPlace().getAddress();
                        mLikelyPlaceAttributions[i] = (String)place.getPlace().getAttributions();
                        mLikelyPlaceLatLngs[i] = (LatLng) place.getPlace().getLatLng();
                        i++;
                        if(i>mMaxEntries-1)
                            break;
                    }
                    placeLikelihoods.release();
                    openPlacesDialog();
                    Log.d(TAG, "onResult: ");
                }
            });
        }else{
//            mMap.addMarker(new MarkerOptions()
//                        .title("Default Info")
//                        .position(mDefaultLocation)
//                        .snippet("Default Snippet"));
        }
    }


    public void openPlacesDialog(){
        Log.d(TAG, "openPlacesDialog: ");

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
            
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: ");
                LatLng markerLatLng = mLikelyPlaceLatLngs[i];
                String markerSnippet = mLikelyPlaceAddresses[i];
                if(mLikelyPlaceAttributions!=null){
                    markerSnippet = markerSnippet+"\n"+mLikelyPlaceAttributions[i];
                }
                mMap.addMarker(new MarkerOptions()
                            .title(mLikelyPlaceNames[i])
                            .position(markerLatLng)
                            .snippet(markerSnippet));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,DEFAULT_ZOOM));
            }
        };

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Places")
                .setItems(mLikelyPlaceNames,listener)
                .show();
    }



    private void setPlacePicker() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this),PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){

                Place place = PlacePicker.getPlace(this,data);
                Toast.makeText(this, "Place :"+place.getName(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void addPlaceAutoCompleteFragment(){

        SupportPlaceAutocompleteFragment autoCompleteFragment = (SupportPlaceAutocompleteFragment) getSupportFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);
        Log.d(TAG, "addPlaceAutoCompleteFragment: ");
        autoCompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(MapsActivity.this, "Place :" +place.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                Log.d(TAG, "onError: ");
            }
        });


    }

    private void getDirections(){

        GoogleDirection googleDirection = RestApi.getInstance().getGoogleDirection();

        LatLng originLatLang = new LatLng(28.6869799,77.1272698);
        LatLng destLatLang = new LatLng(28.697096, 77.142404);
        final String origin = originLatLang.latitude+","+originLatLang.longitude;
        String dest = destLatLang.latitude+","+destLatLang.longitude;
        googleDirection.getDirections(
                origin,
                dest,
                "driving"
        ).enqueue(new Callback<DirectionApi>() {
            @Override
            public void onResponse(Call<DirectionApi> call, Response<DirectionApi> response) {
                Log.d(TAG, "onResponse: "+ response.body());
                polyLineOptions = (new DirectionAdapter()).getLatLngs(response.body());
                for(PolylineOptions options : polyLineOptions){
                    mMap.addPolyline(options.color(Color.RED));
                }
            }

            @Override
            public void onFailure(Call<DirectionApi> call, Throwable t) {

            }
        });

    }
}
