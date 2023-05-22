package com.thanebuhanan.caloriecounter.firebase

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.thanebuhanan.caloriecounter.utils.sendNotification

const val TAG = "bla"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }

    }

    private fun sendNotification(messageBody: String) {
        val notificationManager =
            ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)
        notificationManager?.sendNotification(messageBody, applicationContext)

    }

    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: $token")

        sendRegistrationTokenToServer()
    }

    private fun sendRegistrationTokenToServer() {
        Log.d(TAG, "Sent to server")
    }

}