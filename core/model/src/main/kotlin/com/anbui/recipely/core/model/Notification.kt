package com.anbui.recipely.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


data class Notification(
    val id: String,
    val userId: String,
    val notificationType: NotificationType,
    val time: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    val message: String,
    val isRead: Boolean,
    val imageUrl: String?,
)