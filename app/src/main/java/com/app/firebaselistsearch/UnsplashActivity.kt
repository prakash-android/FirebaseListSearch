package com.app.firebaselistsearch

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
import kotlinx.android.synthetic.main.activity_unsplash.*

class UnsplashActivity : AppCompatActivity() {


    var isMultipleSelection = false
    var REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unsplash)

        UnsplashPhotoPicker.init(
            application, // application
            "KvqcEqOLG_73EToV7g330sgaJ4sLOZKV4kDYq0MyS84",
            "TKaCAQj3IWSPoA4IJ6WgZ-wH4fagNUZ1Xcg2CNBkhl4"
            /* optional page size */
        )

        getImgBtn.setOnClickListener {
            startActivityForResult(
                UnsplashPickerActivity.getStartingIntent(
                    this, // context
                    isMultipleSelection
                ), REQUEST_CODE
            )
        }

    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val photos: ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            // use your photos here
            var temp = photos?.get(0)!!.urls.full
            Log.e("res", "" + temp.toString())

//            UnsplashUrls(thumb=https://images.unsplash.com/photo-1612854931622-b723240cb6f2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwyMDU5OTh8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8&ixlib=rb-1.2.1&q=80&w=200, small=https://images.unsplash.com/photo-1612854931622-b723240cb6f2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwyMDU5OTh8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8&ixlib=rb-1.2.1&q=80&w=400, medium=null, regular=https://images.unsplash.com/photo-1612854931622-b723240cb6f2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwyMDU5OTh8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8&ixlib=rb-1.2.1&q=80&w=1080, large=null, full=https://images.unsplash.com/photo-1612854931622-b723240cb6f2?crop=entropy&cs=srgb&fm=jpg&ixid=MXwyMDU5OTh8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8&ixlib=rb-1.2.1&q=85, raw=https://images.unsplash.com/photo-1612854931622-b723240cb6f2?ixid=MXwyMDU5OTh8MHwxfGNvbGxlY3Rpb258MXwzMTcwOTl8fHx8fDJ8&ixlib=rb-1.2.1)

//            pickImg.setImageDrawable()
            Glide.with(this)
                .load(temp)
                .placeholder(R.drawable.placeholder_bg)
                .error(R.drawable.placeholder_bg)
                .into(pickImg)

        }
    }

}