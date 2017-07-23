package com.example.suraj.lecture9asynchtask

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  button2.setOnClickListener { tvResult.text = "Changed" }
        button.setOnClickListener {
            val syTask = NSecWait(tvResult)
            syTask.execute(Integer.valueOf(editText.text.toString()))
        }

        button2.setOnClickListener {
            V : View -> {

            // mytv!!.text = "Changed"
        }
//            ,i: Integer -> {
//
//            }
        }
    }

    companion object {

        val TAG = "MainActivity"
    }

}
