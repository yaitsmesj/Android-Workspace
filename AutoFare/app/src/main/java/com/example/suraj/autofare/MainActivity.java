package com.example.suraj.autofare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText km;
    EditText minute;
    Button calc;
    TextView tvfare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        km = (EditText)findViewById(R.id.et_km);
        minute = (EditText) findViewById(R.id.et_time);
        calc = (Button) findViewById(R.id.btn_cal);
        tvfare = (TextView)findViewById(R.id.tv_fare);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float distance = Float.valueOf(km.getText().toString());
                float time = Float.valueOf(minute.getText().toString());

                float fare = calcFare(distance,time);

                tvfare.setText(String.valueOf(fare));
            }
        });

    }

//    static float calcFare(float km, float min){
//        return 25 + ((km>2)?((km-2)*8):(0)) + ((min>15)?(min-15):0);
//    }

    static float calcFare(float km,float min){
        float fare = 25;
        if(km>2)
            fare += (km-2)*8;

        if(min>15){
            fare += (min-15);
        }
        return fare;
    }
}
