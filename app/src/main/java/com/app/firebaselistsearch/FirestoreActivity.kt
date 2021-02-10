package com.app.firebaselistsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.firebaselistsearch.adapter.UserAdapter
import com.app.firebaselistsearch.model.Users
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_firestore.*

class FirestoreActivity : AppCompatActivity(), UserAdapter.UpcomingAdapterListener {



    lateinit var userAdapter: UserAdapter

    var userArrayList: ArrayList<Users> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore)
        initUI()
    }


    private fun initUI() {
        //recyclerview
        recyclerView = findViewById<RecyclerView>(R.id.result_list)



        //firestore
        val db = Firebase.firestore

        //read data
       var userRef = db.collection("Users")


            userRef.get()
            .addOnSuccessListener { result ->
                userArrayList.clear()
                for (document in result) {
                    Log.e("TAG", "${document.id} => ${document.data}")
                    userArrayList.add(
                        Users("" + document.data["name"],
                            "" + document.data["image"],
                            "" + document.data["pincode"]
                        )
                    )
                }
                setupRecyclerView()
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error getting documents.", exception)
            }

//       userRef.addSnapshotListener { value, e ->
//                if (e != null) {
//                    Log.e("TAG", "Listen failed.", e)
//                    return@addSnapshotListener
//                }
//
//                userArrayList.clear()
//                for (doc in value!!) {
//                    Log.e("TAG", "${doc.id} => ${doc.data}")
//                    userArrayList.add(
//                        Users("" + doc.data["name"],
//                            "" + doc.data["image"],
//                            "" + doc.data["pincode"]
//                        ))
//                }
//            }
    }



    private fun setupRecyclerView() {
        if(userArrayList.isEmpty()){
            recyclerView.layoutManager = LinearLayoutManager(this)
            userAdapter =
                UserAdapter(
                    userArrayList,
                    this,
                    this@FirestoreActivity
                )
            recyclerView.adapter = userAdapter

        } else {

            recyclerView.layoutManager = LinearLayoutManager(this)
            userAdapter =
                UserAdapter(
                    userArrayList,
                    this,
                    this@FirestoreActivity
                )
            recyclerView.adapter = userAdapter
        }
    }


    override fun onListItemClicked(dummyData: Users, position: Int) {
        //click action

    }


}