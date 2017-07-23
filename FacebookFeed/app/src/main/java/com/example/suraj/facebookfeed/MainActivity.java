package com.example.suraj.facebookfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.lv_feed);
        FeedAdapter feedAdapter = new FeedAdapter(this,generate());
        listView.setAdapter(feedAdapter);


    }

    public ArrayList<String> generate(){
        ArrayList<String> var = new ArrayList<>();
        var.add("This is a Test Field to check the Font Size");
        var.add("Test");
        return var;
    }
}
