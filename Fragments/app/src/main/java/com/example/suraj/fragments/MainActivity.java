package com.example.suraj.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Before layout inflate");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }
}
