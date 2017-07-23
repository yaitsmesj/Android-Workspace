package com.example.suraj.lecture8morefragments;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;

public class Main2Activity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv= (TextView) findViewById(R.id.tv);
        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("World");
                    }
                };
                handler.postDelayed(r,10000);
            }
        });
        ((Button)findViewById(R.id.btnWow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Change");
            }
        });
    }

}
