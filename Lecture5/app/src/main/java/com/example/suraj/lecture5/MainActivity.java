package com.example.suraj.lecture5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> students = generate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        StudentRecycleAdapter studentRecycleAdapter = new StudentRecycleAdapter(this,students);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentRecycleAdapter);
    }

    public ArrayList<Student> generate(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("A","Pandora"));
        students.add(new Student("B","Elixir"));
        students.add(new Student("C","Crux"));
        students.add(new Student("D","Pandora"));
        students.add(new Student("E","Pandora"));
        students.add(new Student("F","LaunchPad"));
        students.add(new Student("G","Pandora"));
        students.add(new Student("A","Pandora"));
        students.add(new Student("B","Elixir"));
        students.add(new Student("C","Crux"));
        students.add(new Student("D","Pandora"));
        students.add(new Student("E","Pandora"));
        students.add(new Student("F","LaunchPad"));
        students.add(new Student("G","Pandora"));
        students.add(new Student("A","Pandora"));
        students.add(new Student("B","Elixir"));
        students.add(new Student("C","Crux"));
        students.add(new Student("D","Pandora"));
        students.add(new Student("E","Pandora"));
        students.add(new Student("F","LaunchPad"));
        students.add(new Student("G","Pandora"));
        students.add(new Student("A","Pandora"));
        students.add(new Student("B","Elixir"));
        students.add(new Student("C","Crux"));
        students.add(new Student("D","Pandora"));
        students.add(new Student("E","Pandora"));
        students.add(new Student("F","LaunchPad"));
        students.add(new Student("G","Pandora"));


        return students;
    }
}
