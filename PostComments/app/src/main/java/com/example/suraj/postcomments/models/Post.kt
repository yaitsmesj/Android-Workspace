package com.example.suraj.postcomments.models

/**
 * Created by Suraj on 6/28/2017.
 */

data class Post(
        val id: kotlin.Int,
        val userId: kotlin.Int,
        val title: String,
        val body: String
)