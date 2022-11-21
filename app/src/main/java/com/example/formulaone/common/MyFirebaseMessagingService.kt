package com.example.formulaone.common

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.formulaone.R

class MyFirebaseMessagingService (
    context: Context
){
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(myContext:Context,race:String){
        val noti = NotificationCompat.Builder(myContext, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_f1_logo)
            .setContentTitle("Race For Tomorrow")
            .setContentText("We are rolling in $race").build()

        notificationManager.notify(1234, noti)
    }

    companion object{
        const val MY_CHANNEL_ID = "my_channel"
    }
}