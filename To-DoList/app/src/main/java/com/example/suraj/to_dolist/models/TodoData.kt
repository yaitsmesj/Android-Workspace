package com.example.suraj.to_dolist.models

/**
 * Created by Suraj on 6/12/2017.
 */

data class TodoData(
        var id: Int?,
        var task:String?,
        var place: String?,
        var time: String?,
        var isAlarm: Int?
)