package com.example.formulaone.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.formulaone.R
import com.example.formulaone.ui.mainFragment.MainFragment
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

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