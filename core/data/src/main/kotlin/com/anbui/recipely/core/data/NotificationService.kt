package com.anbui.recipely.core.data

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationService @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: String, title: String, id: Int) {
        val activityIntent = Intent(
            context,
            (context as Application)::class.java,
        )
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val notification = NotificationCompat.Builder(
            context,
            COUNTER_CHANNEL_ID,
        )
            .setSmallIcon(R.drawable.ic_recipely)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(
            id,
            notification.build(),
        )
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"
    }
}