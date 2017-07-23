package com.example.suraj.lecture10json

/**
 * Created by Suraj on 6/28/2017.
 */

data class PostComment(
        val postId: kotlin.Int,
        val id: kotlin.Int,
        val name: String,
        val email: String,
        val body: String
)