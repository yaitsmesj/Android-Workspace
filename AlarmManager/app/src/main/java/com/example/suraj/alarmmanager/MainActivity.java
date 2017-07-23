package com.example.suraj.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Log.d(TAG, "onCreate: ");

        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(
                this,112,
                i,PendingIntent.FLAG_ONE_SHOT
        );

        am.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime()+(1000*10),
                pi
        );
    }
}
