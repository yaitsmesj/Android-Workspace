package com.example.suraj.lecture6intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
   // EditText etNum1,etNum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

  //      etNum1 = (EditText)findViewById(R.id.et_Num1);
  //      etNum2 = (EditText) findViewById(R.id.et_Num2);
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String num1 = etNum1.getText().toString();
//                String num2 = etNum2.getText().toString();
//                int result = Integer.parseInt(num1)+Integer.parseInt(num2);
//                Intent i = new Intent(MainActivity.this,OtherActivity.class);
//                i.putExtra("result",result);
//                startActivity(i);
            //    finish();

                Intent i = new Intent(MainActivity.this,NewIntentExampleActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
