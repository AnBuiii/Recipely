package com.anbui.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.model.Notification
import com.anbui.model.NotificationType
import java.time.LocalDateTime

@Entity(tableName = "Notification")
data class NotificationEntity(
    @PrimaryKey() @ColumnInfo(name = "_id") val id: String,
    val userId: String,
    val notificationType: com.anbui.model.NotificationType,
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String,
    val isRead: Boolean,
    val imageUrl: String?,
) {
    fun toNotification(): com.anbui.model.Notification {
        return com.anbui.model.Notification(
            id = id,
            userId = userId,
            notificationType = notificationType,
            time = time,
            message = message,
            isRead = isRead,
            imageUrl = imageUrl
        )
    }
}