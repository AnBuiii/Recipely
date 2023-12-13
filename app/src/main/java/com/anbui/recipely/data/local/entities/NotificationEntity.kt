package com.anbui.recipely.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.domain.models.Notification
import com.anbui.recipely.domain.models.NotificationType
import java.time.LocalDateTime

@Entity(tableName = "Notification")
data class NotificationEntity(
    @PrimaryKey() @ColumnInfo(name = "_id") val id: String,
    val userId: String,
    val notificationType: NotificationType,
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String,
    val isRead: Boolean,
    val imageUrl: String?,
) {
    fun toNotification(): Notification {
        return Notification(
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