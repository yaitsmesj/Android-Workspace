package com.example.suraj.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Suraj on 7/6/2017.
 */

public class PermissionManager {

    static ArrayList<PermissionResultListener> listenerList = new ArrayList<>();
    public static final String TAG = "PERM";
    interface PermissionResultListener{
        void onPermissionGranted();
        void onPermissionDenied();
    }

    static void askForPermission(Activity activity, String[] permission, PermissionResultListener prl){

        Log.d(TAG, "askForPermission: ");
        int reqCode = listenerList.size();
        listenerList.add(reqCode,prl);
        // int permCode = ContextCompat.checkSelfPermission(activity, permission);

        ActivityCompat.requestPermissions(activity,permission,reqCode);
        Log.d(TAG, "askForPermission: after request");

    }
    static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        PermissionResultListener prl = listenerList.get(requestCode);
        for(int i=0;i<permissions.length;i++){
            Log.d(TAG, "onRequestPermissionsResult: ");
            if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                prl.onPermissionGranted();
            }
            else{
                prl.onPermissionDenied();
            }
        }
    }
}
