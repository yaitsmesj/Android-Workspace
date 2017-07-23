package com.example.suraj.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    EditText etNum1,etNum2;
    Button btnAdd;
    TextView tvSum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Log.d(TAG,"onCreate: ");
        Log.e(TAG,"ERROR");
        Log.i(TAG,"info");
        Log.v(TAG,"verbose");
        Log.w(TAG,"Warning");
        Log.wtf(TAG,"WTF");

*/

        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        tvSum = (TextView) findViewById(R.id.tvSum);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAndShow();
            }
        });
    }

    public void addAndShow(){
        int a = Integer.valueOf(etNum1.getText().toString());
        int b = Integer.valueOf(etNum2.getText().toString());
        tvSum.setText(String.valueOf(a+b));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");   
    }

    
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
}
