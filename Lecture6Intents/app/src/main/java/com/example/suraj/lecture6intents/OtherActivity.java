package com.example.suraj.lecture6intents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);


        ((TextView)findViewById(R.id.tv_other)).setText(String.valueOf(getIntent().getIntExtra("result",0)));
    }
}
