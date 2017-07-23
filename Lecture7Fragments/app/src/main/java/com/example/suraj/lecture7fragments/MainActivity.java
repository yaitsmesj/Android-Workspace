package com.example.suraj.lecture7fragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Course> arrayList = new ArrayList<>();
        arrayList.add(new Course("Pandora","Arnav","Android"));
        arrayList.add(new Course("Elixir","Arnav","Android"));
        arrayList.add(new Course("LaunchPad","Arnav","Android"));

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        final FragmentManager FragMan = getSupportFragmentManager();

        int[] admissions = new int[3];
        View.OnClickListener courseButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = null;
                int cid = 0;

                switch (v.getId()){
                    case R.id.btn1: course = arrayList.get(0); cid=0; break;
                    case R.id.btn2: course = arrayList.get(1); cid = 1; break;
                    case R.id.btn3: course = arrayList.get(2); cid= 2; break;
                }
                FragMan.beginTransaction().replace(R.id.fragContainer,Course2Fragment.newInstance(course,cid)).commit();
            }
        };
        btn1.setOnClickListener(courseButtonListener);
        btn2.setOnClickListener(courseButtonListener);
        btn3.setOnClickListener(courseButtonListener);

        //final CourseFragment c1Frag = new CourseFragment();
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Course2Fragment cFrag = Course2Fragment.newInstance("Pandora","Arnav","Java");
//                FragMan.beginTransaction().replace(R.id.fragContainer,new Course2Fragment()).commit();
//            //    c1Frag.setData("Pandora","Arnav","Java");
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                c1Frag.setData("Pandora","Arnav","Java");
//            }
//        });
    }
}
