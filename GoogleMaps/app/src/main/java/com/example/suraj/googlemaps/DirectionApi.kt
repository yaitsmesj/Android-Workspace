package com.example.suraj.googlemaps


/**
 * Created by Suraj on 7/15/2017.
 */
data class DirectionApi(
        val routes: Array<Routes>
){
    data class Routes(
            val legs: Array<Legs>
    ){
        data class Legs(
                val steps: Array<Steps>
        ){
            data class Steps(
                    val polyline: Polyline
            ){
                data class Polyline(
                        val points: String
                )
            }
        }
    }
}


