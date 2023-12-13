package com.anbui.recipely.domain.models

import java.time.LocalDateTime

data class Notification(
    val id: String,
    val userId: String,
    val notificationType: NotificationType,
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String,
    val isRead: Boolean,
    val imageUrl: String?,
)

val exampleNotifications = listOf(
    Notification(
        id = "exampleNotification1",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = "https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=780&q=80"
    ),
    Notification(
        id = "exampleNotification2",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        time = LocalDateTime.now().minusDays(2),
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = null
    ),
    Notification(
        id = "exampleNotification3",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = null
    ),
    Notification(
        id = "exampleNotification4",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = null
    ),
    Notification(
        id = "exampleNotification5",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = null
    ),
    Notification(
        id = "exampleNotification6",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = null
    ),
    Notification(
        id = "exampleNotification7",
        userId = "exampleUser1",
        notificationType = NotificationType.Like,
        message = "Beef Burger chesse homemade",
        isRead = false,
        imageUrl = null
    )
)
