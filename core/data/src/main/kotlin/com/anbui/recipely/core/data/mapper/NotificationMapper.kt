package com.anbui.recipely.core.data.mapper

import com.anbui.recipely.core.database.entities.NotificationEntity
import com.anbui.recipely.core.model.Notification

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