package com.example.suraj.to_dolist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Suraj on 7/22/2017.
 */
class DatabaseHelper(var context:Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VER){


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(TodoTable.CMD_CREATE_TODO_TABLE)
     }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1 && newVersion == 2) {
         //   db.execSQL(TodoTable.UPGRADE_TABLE_1_2)
        }
    }
    companion object {
        val TAG = "TODO"
        val DB_NAME = "todo.db"
        val DB_VER = 1;
    }

}