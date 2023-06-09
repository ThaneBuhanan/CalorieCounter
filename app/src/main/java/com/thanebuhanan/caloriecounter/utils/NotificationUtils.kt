package com.thanebuhanan.caloriecounter.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.thanebuhanan.caloriecounter.MainActivity
import com.thanebuhanan.caloriecounter.R

private val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
    )

    val bicepImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.bicep
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(bicepImage)
        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    )
        .setSmallIcon(R.drawable.bicep)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(bicepImage)
        .setPriority(NotificationCompat.PRIORITY_MIN)

    notify(NOTIFICATION_ID, builder.build())
}
