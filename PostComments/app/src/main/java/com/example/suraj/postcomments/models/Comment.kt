package com.example.suraj.postcomments.models

/**
 * Created by Suraj on 6/28/2017.
 */

data class Comment(
        val id: kotlin.Int,
        val postId: kotlin.Int,
        val name: String,
        val email: String,
        val body: String
)