package com.example.suraj.to_dolist;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suraj.to_dolist.adapters.TodoRecycleAdapter;
import com.example.suraj.to_dolist.database.DatabaseHelper;
import com.example.suraj.to_dolist.database.TodoTable;
import com.example.suraj.to_dolist.models.TodoData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    private ArrayList<TodoData> todoData = new ArrayList<>();

//    public static String TAG = "MainActivity";
    private TodoRecycleAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_todo);
        todoAdapter = new TodoRecycleAdapter(this, todoData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todoAdapter);

        db = (new DatabaseHelper(this)).getWritableDatabase();
        updateList();

        Button addButton = (Button) findViewById(R.id.bt_add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et_task = ((EditText) findViewById(R.id.et_task));
                EditText et_place = ((EditText) findViewById(R.id.et_place));
                EditText et_time = ((EditText) findViewById(R.id.et_time));
                CheckBox checkBox = (CheckBox) findViewById(R.id.cb_alarm);
                String task = (et_task.getText()).toString();
                String place = (et_place.getText()).toString();
                String time = (et_time.getText()).toString();

                if (task.equals("") || place.equals("") || time.equals("")) {
                    Toast.makeText(MainActivity.this, "Please Fill all the Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                TodoData newData = new TodoData(null,null,null,null,null);
                newData.setTask(task);
                newData.setPlace(place);
                newData.setTime(time);
                if(checkBox.isChecked())
                    newData.setAlarm(1);
                else
                    newData.setAlarm(0);

                TodoTable.addTodo(db,newData);
                updateList();
                et_task.setText("");
                et_place.setText("");
                et_time.setText("");
                checkBox.setChecked(false);
                et_task.requestFocus();

                Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<TodoData> generate() {
        //   ArrayList<TodoData> todo = new ArrayList<>();
  /*      todo.add(new TodoData("Walk","Park","12:50",true));
        todo.add(new TodoData("Sleep","Room","02:50",false));
        todo.add(new TodoData("Walk","Road","12:50",true));
        todo.add(new TodoData("Study","Park","12:50",true));
        todo.add(new TodoData("Walk","Park","12:50",true));
        todo.add(new TodoData("Run","Park","12:50",false));
        todo.add(new TodoData("Walk","Park","12:50",true));
        todo.add(new TodoData("Play","Park","12:50",true));
        todo.add(new TodoData("Walk","Park","12:50",true));
        todo.add(new TodoData("Meeting","Office","12:50",false));
    */
        return new ArrayList<>();
    }
    public void updateList(){

        todoData.clear();
        todoData.addAll(TodoTable.getTodos(db));
        todoAdapter.notifyDataSetChanged();
    }
}
