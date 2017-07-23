package com.example.suraj.to_dolist.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.suraj.to_dolist.models.TodoData;

import java.util.ArrayList;

import static com.example.suraj.to_dolist.database.DBUtils.COMMA;
import static com.example.suraj.to_dolist.database.DBUtils.CREATE_TABLE;
import static com.example.suraj.to_dolist.database.DBUtils.LBR;
import static com.example.suraj.to_dolist.database.DBUtils.RBR;
import static com.example.suraj.to_dolist.database.DBUtils.SEMI;
import static com.example.suraj.to_dolist.database.DBUtils.TYPE_INTEGER;
import static com.example.suraj.to_dolist.database.DBUtils.TYPE_INTEGER_PK_AI;
import static com.example.suraj.to_dolist.database.DBUtils.TYPE_TEXT;

/**
 * Created by Suraj on 7/22/2017.
 */

public class TodoTable {

    public static final String TABLE_NAME = "todos";
    public static final String TAG = "Database";
    interface Columns{
        String COL_ID = "id";
        String COL_TASK = "task";
        String COL_PLACE = "place";
        String COL_TIME = "time";
        String COL_ISALARM = "isAlarm";
    }


    public static final String CMD_CREATE_TODO_TABLE =
            CREATE_TABLE +
                    TABLE_NAME +
                    LBR +
                    Columns.COL_ID + TYPE_INTEGER_PK_AI + COMMA +
                    Columns.COL_TASK + TYPE_TEXT + COMMA +
                    Columns.COL_PLACE + TYPE_TEXT + COMMA +
                    Columns.COL_TIME + TYPE_TEXT + COMMA +
                    Columns.COL_ISALARM + TYPE_INTEGER +
                    RBR + SEMI;

    public static ArrayList<TodoData> getTodos(SQLiteDatabase db){

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        Columns.COL_ID,
                        Columns.COL_TASK,
                        Columns.COL_PLACE,
                        Columns.COL_TIME,
                        Columns.COL_ISALARM
                },null,null,null,null,null
        );

        ArrayList<TodoData> todoDatas = new ArrayList<>();
        int idIndex = cursor.getColumnIndex(Columns.COL_ID);
        int taskIndex = cursor.getColumnIndex(Columns.COL_TASK);
        int placeIndex = cursor.getColumnIndex(Columns.COL_PLACE);
        int timeIndex = cursor.getColumnIndex(Columns.COL_TIME);
        int isAlarmIndex = cursor.getColumnIndex(Columns.COL_ISALARM);

        while (cursor.moveToNext()){

            TodoData todoData = new TodoData(null,null,null,null,null);
            todoData.setId(cursor.getInt(idIndex));
            todoData.setTask(cursor.getString(taskIndex));
            todoData.setPlace(cursor.getString(placeIndex));
            todoData.setTime(cursor.getString(timeIndex));
            todoData.setAlarm(cursor.getInt(isAlarmIndex));
            todoDatas.add(todoData);
        }
        return todoDatas;
    }

    public static void addTodo(SQLiteDatabase db,TodoData todoData){
        ContentValues todoToInsert = new ContentValues();

        todoToInsert.put(Columns.COL_TASK,todoData.getTask());
        todoToInsert.put(Columns.COL_PLACE,todoData.getPlace());
        todoToInsert.put(Columns.COL_TIME,todoData.getTime());
        todoToInsert.put(Columns.COL_ISALARM,todoData.isAlarm());

        long id = db.insert(TABLE_NAME,null,todoToInsert);
        Log.d(TAG, "addTodo: "+id);
    }

}
