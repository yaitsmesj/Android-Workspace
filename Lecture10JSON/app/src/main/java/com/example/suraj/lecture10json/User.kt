package com.example.suraj.lecture10json


/**
 * Created by Suraj on 6/28/2017.
 */

data class User(
        val name: String,
        val address: Address
)
{
    data class Address(
            val street: String,
            val suite: String,
            val city: String
    )
}