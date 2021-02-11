package com.app.firebaselistsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestoreBtn.setOnClickListener {
            var intent = Intent(this, FirestoreActivity::class.java)
            startActivity(intent)
        }

        realdbBtn.setOnClickListener {
            var intent = Intent(this, RealtimeDbActivity::class.java)
            startActivity(intent)
        }

        unsplashBtn.setOnClickListener {
            var intent = Intent(this, UnsplashActivity::class.java)
            startActivity(intent)
        }

    }







}