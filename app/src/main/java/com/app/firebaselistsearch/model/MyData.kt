package com.app.firebaselistsearch.model



data class Users(
    val name: String,
    val image: String,
    val pincode: String
)


data class MyUser(
    val id : String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avater: String
)