
package com.example.suraj.lecture8services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public static final String TAG="SRV";
    public MyService() {
        Log.d(TAG, "MyService: ");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {

        Log.d(TAG, "onStartCommand: ");
        String message = " nothing";
        if(intent!=null){
            message = intent.getStringExtra("hello");
        }
        Toast.makeText(this,"OnStartCommand"+message,Toast.LENGTH_SHORT).show();

        Long startTime = System.currentTimeMillis();
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },5000);
        //stopSelf();
        //return super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
