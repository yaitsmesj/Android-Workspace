package com.example.suraj.servicespractise;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public static final String TAG = "Service 1";
    public MyService() {
        Log.d(TAG, "MyService: ");
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        String message = " nothing";
        if(intent!=null){
            message = intent.getStringExtra("hello");
        }
        Toast.makeText(this,"OnStartCommand"+message,Toast.LENGTH_SHORT).show();


        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MyService.this,MyService2.class);
                i.putExtra("hello","Service 1");
                startService(i);
            }
        },5000);
    //   return super.onStartCommand(intent, flags, startId);
    return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "onTaskRemoved: ");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
