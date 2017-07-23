package com.example.suraj.postcomments.models

/**
 * Created by Suraj on 6/28/2017.
 */

data class User(
        val id: kotlin.Int,
        val name: String,
        val username: String,
        val email: String,
        val address: Address
) {
    data class Address(
            val street: String,
            val suite: String,
            val city: String
    )
}