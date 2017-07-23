package com.example.suraj.lecture4list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> cbCourses = generateCourses();
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView courseListView = (ListView) findViewById(R.id.lv_courses);
//        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,cbCourses);
//        courseListView.setAdapter(courseAdapter);

        courseListView.setAdapter(new CourseAdapter(this,cbCourses));
    }

    static ArrayList<Course> generateCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        courses.add(new Course("Pandora","Arnav",3,"Pitampura"));
        courses.add(new Course("Elixir","Arnav",3,"Pitampura"));
        courses.add(new Course("Pandora","Harshit",1,"Dwarka"));
        courses.add(new Course("Elixir","Ayush",3,"Pitampura"));
        courses.add(new Course("Launchpad","Prateek",2,"Pitampura"));
        courses.add(new Course("Crux","Sumeet",3,"Pitampura"));
        courses.add(new Course("Algos","Prateek",4,"Pitampura"));

        courses.add(new Course("Pandora","Arnav",3,"Pitampura"));
        courses.add(new Course("Elixir","Arnav",3,"Pitampura"));
        courses.add(new Course("Pandora","Harshit",1,"Dwarka"));
        courses.add(new Course("Elixir","Ayush",3,"Pitampura"));
        courses.add(new Course("Launchpad","Prateek",2,"Pitampura"));
        courses.add(new Course("Crux","Sumeet",3,"Pitampura"));
        courses.add(new Course("Algos","Prateek",4,"Pitampura"));

        return courses;
    }



    }


