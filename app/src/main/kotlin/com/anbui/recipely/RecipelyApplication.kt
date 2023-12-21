package com.anbui.recipely

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.anbui.recipely.core.data.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipelyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.COUNTER_CHANNEL_ID,
            "RecipelyNotificationChannel",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Notify for Recipely"
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
