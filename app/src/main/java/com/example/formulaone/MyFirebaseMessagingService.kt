package com.example.formulaone

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService :FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("cicisha","$message.data")
    }
}