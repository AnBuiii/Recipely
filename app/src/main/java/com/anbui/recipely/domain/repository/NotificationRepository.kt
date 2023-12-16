package com.anbui.recipely.domain.repository

import com.anbui.recipely.core.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getCurrentUserNotification(): Flow<List<Notification>>

    suspend fun insertNotification(notification: Notification)

    suspend fun readNotification(notificationId: String)
}