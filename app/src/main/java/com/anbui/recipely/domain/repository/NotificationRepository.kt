package com.anbui.recipely.domain.repository

import com.anbui.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getCurrentUserNotification(): Flow<List<com.anbui.model.Notification>>

    suspend fun insertNotification(notification: com.anbui.model.Notification)

    suspend fun readNotification(notificationId: String)
}