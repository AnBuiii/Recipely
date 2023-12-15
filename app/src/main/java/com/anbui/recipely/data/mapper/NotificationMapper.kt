package com.anbui.recipely.data.mapper

import com.anbui.model.Notification
import com.anbui.database.NotificationEntity

fun Notification.toNotificationEntity(): com.anbui.database.NotificationEntity {
    return com.anbui.database.NotificationEntity(
        id = id,
        userId = userId,
        notificationType = notificationType,
        time = time,
        message = message,
        isRead = isRead,
        imageUrl = imageUrl
    )
}