package com.app.firebaselistsearch.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


data class Users(
    val name: String,
    val image: String,
    val pincode: String
)


//data class MyUser(
//    val id: String,
//    val first_name: String,
//    val last_name: String,
//    val email: String,
//    val avater: String
//)


@IgnoreExtraProperties
class MyUser {
    var id: String? = null
    var first_name: String? = null
    var last_name: String? = null
    var email: String? = null
    var avater: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    constructor(id: String?, first_name: String?, last_name: String?, email: String?, avater: String?) {
        this.id = id
        this.first_name = first_name
        this.last_name = last_name
        this.email = email
        this.avater = avater
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        val result: HashMap<String, Any?> = HashMap()
        result["id"] = id
        result["first_name"] = first_name
        result["last_name"] = last_name
        result["email"] = email
        result["avater"] = avater
        return result
    }
}