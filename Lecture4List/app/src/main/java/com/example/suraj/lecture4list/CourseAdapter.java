package com.example.suraj.lecture4list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Suraj on 6/14/2017.
 */

public class CourseAdapter extends BaseAdapter {

    StudentViewHolder studentViewHolder;
    Context context;
    ArrayList<Course> cbCourses;
    public CourseAdapter(Context context, ArrayList<Course> cbCourses){
        this.context=context;
        this.cbCourses = cbCourses;
    }

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
        if (position % 2 == 0)
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
        LayoutInflater li =(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);


        Course thisCourse = (Course) getItem(position);
        if (convertView == null) {
            if (position % 2 == 0)
                convertView = li.inflate(R.layout.list_item_course, null);
                //Log.d(TAG,position+ " "+ convertView);
            else
                convertView = li.inflate(R.layout.list_item_course_even, null);

            studentViewHolder = new StudentViewHolder();
            studentViewHolder.tv_courseName = (TextView) convertView.findViewById(R.id.tv_courseName);
            studentViewHolder.tv_courseTeacher = (TextView) convertView.findViewById(R.id.tv_courseTeacher);
            studentViewHolder.tv_batches = (TextView) convertView.findViewById(R.id.tv_batches);
            studentViewHolder.tv_centerName = (TextView) convertView.findViewById(R.id.tv_centerName);
            convertView.setTag(studentViewHolder);
        } else {
            studentViewHolder = (StudentViewHolder) convertView.getTag();
        }

        studentViewHolder.tv_courseName.setText(thisCourse.getName());
        studentViewHolder.tv_courseTeacher.setText(thisCourse.getTeacher());
        studentViewHolder.tv_batches.setText(String.valueOf(thisCourse.getBatches()));
        studentViewHolder.tv_courseName.setText(thisCourse.getCenter());

        return convertView;
    }
    static class StudentViewHolder{
        public TextView tv_courseName;
        public TextView tv_courseTeacher;
        public TextView tv_batches;
        public TextView tv_centerName;

    }


}