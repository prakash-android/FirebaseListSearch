package com.app.firebaselistsearch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.firebaselistsearch.adapter.MyUserAdapter
import com.app.firebaselistsearch.model.MyUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RealtimeDbActivity : AppCompatActivity(), MyUserAdapter.UpcomingAdapterListener {


    lateinit var myUserAdapter: MyUserAdapter
    var userArrayList: ArrayList<MyUser> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var mMessageReference: DatabaseReference
    lateinit var mMessageListener: ChildEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime_db)
        initUI()
    }

    private fun initUI() {
        //recyclerview
        recyclerView = findViewById<RecyclerView>(R.id.result_list)

        mDatabase = Firebase.database.reference

         mMessageReference = mDatabase.child("data")

        val childEventListener: ChildEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                // A new message has been added
                // onChildAdded() will be called for each node at the first time
                val myUser: MyUser? = dataSnapshot.getValue(MyUser::class.java)
                userArrayList.add(myUser!!)
                Log.e("TAG", "onChildAdded:" + myUser!!.first_name)
//                val latest: MyUser = messageList.get(messageList.size() - 1)
//                tvAuthor.setText(latest.author)
//                tvTime.setText(latest.time)
//                tvBody.setText(latest.body)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.e("TAG", "onChildChanged:" + dataSnapshot.key)

                // A message has changed
                val myUser: MyUser? = dataSnapshot.getValue(MyUser::class.java)
                Toast.makeText(
                    this@RealtimeDbActivity,
                    "onChildChanged: " + myUser!!.first_name,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.e("TAG", "onChildRemoved:" + dataSnapshot.key)

                // A message has been removed
                val myUser: MyUser? = dataSnapshot.getValue(MyUser::class.java)
                Toast.makeText(
                    this@RealtimeDbActivity,
                    "onChildRemoved: " + myUser!!.first_name,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.e("TAG", "onChildMoved:" + dataSnapshot.key)

                // A message has changed position
                val myUser: MyUser? = dataSnapshot.getValue(MyUser::class.java)
                Toast.makeText(
                    this@RealtimeDbActivity,
                    "onChildMoved: " + myUser!!.first_name,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("TAG", "postMessages:onCancelled", databaseError.toException())
                Toast.makeText(this@RealtimeDbActivity, "Failed to load Message.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        mMessageReference.addChildEventListener(childEventListener)
        // copy for removing at onStop()
        mMessageListener = childEventListener

        setupRecyclerView()

    }


    override fun onDestroy() {
        super.onDestroy()
        if (mMessageListener != null) {
            mMessageReference.removeEventListener(mMessageListener)
        }
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