package com.example.suraj.lecture5;

/**
 * Created by Suraj on 6/15/2017.
 */

public class Student {
    private String Name;
    private String Batch;

    public String getName() {
        return Name;
    }

    public String getBatch() {
        return Batch;
    }

    public Student(String name, String batch) {
        Name = name;
        Batch = batch;

    }
}
