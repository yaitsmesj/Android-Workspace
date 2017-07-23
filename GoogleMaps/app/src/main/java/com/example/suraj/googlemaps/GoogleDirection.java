package com.example.suraj.googlemaps;

import com.google.android.gms.maps.model.LatLng;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Suraj on 7/16/2017.
 */

public interface GoogleDirection {

    @GET("/maps/api/directions/json")
    Call<DirectionApi> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("mode") String mode
            );
}
