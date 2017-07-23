package com.example.suraj.lecture5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Suraj on 6/15/2017.
 */

public class StudentRecycleAdapter extends RecyclerView.Adapter<StudentRecycleAdapter.StudentViewHolder> {

    Context context;
    ArrayList<Student> students;
    StudentViewHolder studentViewHolder;
    public StudentRecycleAdapter(Context context,ArrayList<Student> students){
        this.context = context;
        this.students = students;
    }

    @Override
    public int getItemViewType(int position) {
        Student student = students.get(position);
        if(student.getBatch().equals("Pandora")){
            return 0;
        }else
            return 1;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutType = 0;
        if(viewType==0)
            layoutType=R.layout.student_list;
        else
            layoutType=R.layout.student_list_2;
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewItem = layoutInflater.inflate(layoutType,parent,false);
        studentViewHolder = new StudentViewHolder(viewItem);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {

        Student student = students.get(position);
        holder.tv_name.setText(student.getName());
        holder.tv_batch.setText(student.getBatch());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_batch;


        public StudentViewHolder(View itemView){
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_batch = (TextView) itemView.findViewById(R.id.tv_batch);

        }
    }

}
