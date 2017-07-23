package com.example.suraj.googlemaps;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.suraj.googlemaps.MapsActivity.TAG;

/**
 * Created by Suraj on 7/16/2017.
 */

public class DirectionAdapter {

    ArrayList<PolylineOptions> arrayList = new ArrayList<>();
    public ArrayList<PolylineOptions> getLatLngs(DirectionApi response){

        DirectionApi.Routes routes[] = response.getRoutes();
        Log.d(TAG, "getLatLngs: "+routes.length);

        for(DirectionApi.Routes eachRoute: routes){

            DirectionApi.Routes.Legs legs[] = eachRoute.getLegs();
            Log.d(TAG, "getLatLngs: "+legs.length);
            for(DirectionApi.Routes.Legs eachLeg: legs){

                DirectionApi.Routes.Legs.Steps steps[] = eachLeg.getSteps();
                Log.d(TAG, "getLatLngs: "+steps.length);
                for(DirectionApi.Routes.Legs.Steps eachStep : steps){

                    DirectionApi.Routes.Legs.Steps.Polyline polyline = eachStep.getPolyline();
                    String point = polyline.getPoints();

                    List<LatLng> latLngs= PolyUtil.decode(point);
                    PolylineOptions polylineOptions = new PolylineOptions().addAll(latLngs);
                    arrayList.add(polylineOptions);
                   // Log.d(TAG, "getLatLngs: "+ arrayList.size());

                }
            }
        }

        return arrayList;
    }
}
