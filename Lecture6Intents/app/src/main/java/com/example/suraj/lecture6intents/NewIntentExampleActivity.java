package com.example.suraj.lecture6intents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewIntentExampleActivity extends AppCompatActivity {

    EditText etaddress;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_intent_example);


        etaddress = (EditText) findViewById(R.id.et_address);
        btn = (Button)findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = etaddress.getText().toString();
                try {
                    Intent i = new Intent(getIntent().ACTION_VIEW, Uri.parse(link));
                    startActivity(i);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(NewIntentExampleActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
