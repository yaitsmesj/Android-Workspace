package com.example.suraj.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "PERM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager.askForPermission(this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ANSWER_PHONE_CALLS,
                        Manifest.permission.READ_CALENDAR
                    },
                new PermissionManager.PermissionResultListener() {
            @Override
            public void onPermissionGranted() {
                Log.d(TAG, "onPermissionGranted: ");
            }

            @Override
            public void onPermissionDenied() {
                Log.d(TAG, "onPermissionDenied: ");
            }
        });

//        PermissionManager.askForPermission(this,
//                new String[]{
//                        Manifest.permission.ANSWER_PHONE_CALLS
//                   },
//                new PermissionManager.PermissionResultListener() {
//                    @Override
//                    public void onPermissionGranted() {
//                        Log.d(TAG, "onPermissionGranted: Phone");
//                    }
//
//                    @Override
//                    public void onPermissionDenied() {
//                        Log.d(TAG, "onPermissionDenied: Phone ");
//                    }
//                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    //
//        int permCode = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//
//        if(permCode == PackageManager.PERMISSION_GRANTED){
//            Log.d(TAG, "onCreate: Permission Granted");
//            Log.d(TAG, "onCreate: We are writing a file here");
//        }
//        if(permCode == PackageManager.PERMISSION_DENIED){
//            Log.d(TAG, "onCreate: Permission Denied");
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{
//                            Manifest.permission.READ_EXTERNAL_STORAGE,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    },345
//                    );
//            Log.d(TAG, "onCreate: We are writing file after getting permission");
//        }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if(requestCode == 345){
//            //means this was our request on line 32
//            for(int i = 0;i < permissions.length;i++){
//                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
//                    Log.d(TAG, "onRequestPermissionsResult: "+ permissions[i]+" was granted");
//
//                    if(permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
//                        Log.d(TAG, "onRequestPermissionsResult: We are writing file after getting permission");
//                    }
//                }
//                if(grantResults[i]==PackageManager.PERMISSION_DENIED){
//                    Log.d(TAG, "onRequestPermissionsResult: "+ permissions[i]+ " was denied");
//                }
//            }
//
//        }
//
//    }

}