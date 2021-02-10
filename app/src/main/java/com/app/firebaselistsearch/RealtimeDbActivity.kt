package com.app.firebaselistsearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.firebaselistsearch.adapter.MyUserAdapter
import com.app.firebaselistsearch.model.MyUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase


class RealtimeDbActivity : AppCompatActivity(), MyUserAdapter.UpcomingAdapterListener {


    lateinit var myUserAdapter: MyUserAdapter
    var userArrayList: ArrayList<MyUser> = ArrayList()
    lateinit var recyclerView: RecyclerView
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime_db)
        initUI()
    }

    private fun initUI() {

        database = Firebase.database.reference

        var dataref = database.child("data")
        Log.e("res", "" + dataref.toString())




    }


    private fun setupRecyclerView() {
        if(userArrayList.isEmpty()){
            recyclerView.layoutManager = LinearLayoutManager(this)
            myUserAdapter =
                MyUserAdapter(
                    userArrayList,
                    this,
                    this@RealtimeDbActivity
                )
            recyclerView.adapter = myUserAdapter

        } else {

            recyclerView.layoutManager = LinearLayoutManager(this)
            myUserAdapter =
                MyUserAdapter(
                    userArrayList,
                    this,
                    this@RealtimeDbActivity
                )
            recyclerView.adapter = myUserAdapter
        }
    }


    override fun onListItemClicked(dummyData: MyUser, position: Int) {
        //click
    }
}