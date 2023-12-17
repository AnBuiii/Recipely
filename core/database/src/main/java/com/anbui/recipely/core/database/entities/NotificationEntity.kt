package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.core.model.Notification
import com.anbui.recipely.core.model.NotificationType
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "Notification")
data class NotificationEntity(
    @PrimaryKey() @ColumnInfo(name = "_id") val id: String,
    val userId: String,
    val notificationType: NotificationType,
    val time: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
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