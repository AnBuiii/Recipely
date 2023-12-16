package com.anbui.recipely.data.mapper

import com.anbui.recipely.core.model.Notification
import com.anbui.recipely.core.database.entities.NotificationEntity

fun Notification.toNotificationEntity(): NotificationEntity {
    return NotificationEntity(
        id = id,
        userId = userId,
        notificationType = notificationType,
        time = time,
        message = message,
        isRead = isRead,
        imageUrl = imageUrl
    )
}