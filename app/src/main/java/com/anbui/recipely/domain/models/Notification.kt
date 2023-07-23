package com.anbui.recipely.domain.models

data class Notification(
    val id : String,
    val userId: String,
    val parentId: String,
    val notificationType: NotificationType,
    val formattedTime: String,
    val message: String,
    val read: Boolean,
    val imageUrl: String?,
    val timeGroup: String,
)

val exampleNotifications = listOf(
    Notification(
        id = "exampleNotification1",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.CommentedOnRecipe,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = "https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=780&q=80",
        timeGroup = "Today"
    ),
    Notification(
        id = "exampleNotification2",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Today"
    ),
    Notification(
        id = "exampleNotification3",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Today"
    ),
    Notification(
        id = "exampleNotification4",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Today"
    ),
    Notification(
        id = "exampleNotification5",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Today"
    ),
    Notification(
        id = "exampleNotification6",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Yesterday"
    ),
    Notification(
        id = "exampleNotification7",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Yesterday"
    ),
    Notification(
        id = "exampleNotification8",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Yesterday"
    ),
    Notification(
        id = "exampleNotification9",
        userId = "exampleUser1",
        parentId = "exampleRecipe1",
        notificationType = NotificationType.Promo,
        formattedTime = "2m ago",
        message =  "Beef Burger chesse homemade",
        read = false,
        imageUrl = null,
        timeGroup = "Yesterday"
    ),
)