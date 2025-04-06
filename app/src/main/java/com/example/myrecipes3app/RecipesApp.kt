package com.example.myrecipes3app

import android.app.Application
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApp : Application()


@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "FCM token : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM", "From ${message.from}")

        message.data.isNotEmpty().let {
            Log.d("FCM", "Message data payload: "+message.data)
        }

        message.notification?.let {
            Log.d("FCM", "Msg notification body : ${it.body}")
        }
    }
}