package com.example.suraj.book;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data;
    Button btn1, btn2, btn3;
    Boolean btn1Status = false, btn2Status = false, btn3Status = false;
    int cid = 0;
    private String FragTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        data.add("This is First Page");
        data.add("This is Second Page");
        data.add("This is Third Page");

        final FragmentManager fragMan = getSupportFragmentManager();


        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PagesFragment frag=null;
                String pageData = "";
                switch (v.getId()) {

                    case R.id.btn1:
                        frag = (PagesFragment)fragMan.findFragmentByTag("first");
                        if(frag==null) {
                            pageData = data.get(0);
                            cid = 0;
                            FragTag = "first";
                        }
                        break;

                    case R.id.btn2:
                        frag = (PagesFragment)fragMan.findFragmentByTag("second");
                        if(frag==null) {
                            pageData = data.get(1);
                            cid = 1;
                            FragTag = "second";
                        }
                            break;
                    case R.id.btn3:
                        frag = (PagesFragment)fragMan.findFragmentByTag("third");
                        if(frag==null) {
                            pageData = data.get(2);
                            cid = 2;
                            FragTag = "third";
                        }
                            break;
                }
                if(frag==null) {
                    fragMan.beginTransaction().add(R.id.fragContainer, PagesFragment.newInstance(pageData, cid, new PagesFragment.starClickListener() {
                        @Override
                        public void addStar() {
                            addChanges();
                        }
                    })).commit();
                }
                else {
                    fragMan.beginTransaction().show(frag).commit();
                }
            }
        };

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(btnClickListener);
        btn2.setOnClickListener(btnClickListener);
        btn3.setOnClickListener(btnClickListener);
    }
    public void addChanges() {
        switch (cid) {
            case 0:
                if (btn1Status) {
                    btn1.setBackgroundResource(android.R.drawable.btn_default);
                    btn1Status = false;
                } else {
                    btn1.setBackgroundColor(Color.BLUE);
                    btn1Status = true;
                }
                break;
            case 1:
                if (btn2Status) {
                    btn2.setBackgroundResource(android.R.drawable.btn_default);
                    btn2Status = false;
                } else {
                    btn2.setBackgroundColor(Color.BLUE);
                    btn2Status = true;
                }
                break;
            case 2:
                if (btn3Status) {
                    btn3.setBackgroundResource(android.R.drawable.btn_default);
                    btn3Status = false;
                } else {
                    btn3.setBackgroundColor(Color.BLUE);
                    btn3Status = true;
                }
                break;
        }

    }
}


