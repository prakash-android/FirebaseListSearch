package com.app.firebaselistsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.firebaselistsearch.FirestoreActivity
import com.app.firebaselistsearch.R
import com.app.firebaselistsearch.model.Users
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.empty_folder_file_list.view.*
import kotlinx.android.synthetic.main.user_item.view.*


class UserAdapter(val usersList: List<Users>, val context : Context, val activity: FirestoreActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var itemFlag: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: RecyclerView.ViewHolder

        if (itemFlag == 0) {
            view =
                EmptyHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.empty_folder_file_list,
                        parent,
                        false
                    )
                )
        } else {
            view = ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.user_item,
                    parent,
                    false
                )
            )
        }

        return view
    }

    override fun getItemCount(): Int {
        itemFlag = usersList.size
        return if (usersList.size > 0) usersList.size else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (itemFlag != 0) {

            val viewHolder: ViewHolder = (holder as ViewHolder)
            viewHolder.title!!.text = usersList[position].name
            viewHolder.desc!!.text = usersList[position].pincode
            val thumbnailUrl = usersList[position].image

            if (thumbnailUrl != null){
                Glide.with(context)
                    .load(thumbnailUrl)
                    .placeholder(R.drawable.placeholder_bg)
                    .error(R.drawable.placeholder_bg)
                    .into(holder.pdfImage)
            }


        } else if(itemFlag == 0) {
            val emptyViewHolder: EmptyHolder = (holder as EmptyHolder)

//                emptyViewHolder.emptyImage.setImageResource(R.drawable.ic_notification)
            emptyViewHolder.emptyText.text = "No Magazine Found"

        }

    }


    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.userName
        val desc = itemView.userBio
        val pdfImage = itemView.userImage


        init {
            itemView.setOnClickListener {
                activity.onListItemClicked(usersList[adapterPosition],adapterPosition)
            }
        }
    }

    class EmptyHolder(view: View) : RecyclerView.ViewHolder(view){
        val emptyImage = itemView.empty_imageView
        val emptyText = itemView.empty_textView
    }

    interface UpcomingAdapterListener {

        fun onListItemClicked(dummyData: Users,position: Int)

    }

}