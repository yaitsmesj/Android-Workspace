package com.example.suraj.lecture3list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    String[] cbCourses = {
//            "Pandora","Arnav","3","Pitampura"
//    };
    ArrayList<Course> cbCourses = generateCourses();
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView courseListView = (ListView) findViewById(R.id.lv_courses);
//        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,cbCourses);
//        courseListView.setAdapter(courseAdapter);

        CourseAdapter courseAdapter = new CourseAdapter();
        courseListView.setAdapter(courseAdapter);
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

    class CourseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cbCourses.size();
        }

        @Override
        public Object getItem(int position) {
            return cbCourses.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if(position%2==0)
                return 0;
                //Log.d(TAG,position+ " "+ convertView);
            else
                return 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li =  getLayoutInflater();           //(LayoutInflater)MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);


            Course thisCourse = (Course) getItem(position);
            if(convertView==null) {
                if(position%2==0)
                    convertView = li.inflate(R.layout.list_item_course, null);
                //Log.d(TAG,position+ " "+ convertView);
                else
                    convertView = li.inflate(R.layout.list_item_course_even,null);
            }
            ((TextView)convertView.findViewById(R.id.tv_courseName)).setText(thisCourse.getName());
            ((TextView)convertView.findViewById(R.id.tv_courseTeacher)).setText(thisCourse.getTeacher());
            ((TextView)convertView.findViewById(R.id.tv_batches)).setText(String.valueOf(thisCourse.getBatches()));
            ((TextView)convertView.findViewById(R.id.tv_centerName)).setText(thisCourse.getCenter());

            return convertView;
        }
    }
}
