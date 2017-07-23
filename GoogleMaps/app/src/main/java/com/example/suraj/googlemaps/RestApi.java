package com.example.suraj.googlemaps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Suraj on 6/30/2017.
 */

public class RestApi {
    private final GoogleDirection googleDirection;
    private static RestApi restApi;
    private final Retrofit retrofit;

    public GoogleDirection getGoogleDirection() {
        return googleDirection;
    }

    private RestApi(){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com")
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    )
                    .build();
        googleDirection = retrofit.create(GoogleDirection.class);
    }
    public static RestApi getInstance(){
        if(restApi==null){
            restApi = new RestApi();
        }
        return restApi;
    }
}
