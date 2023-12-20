package com.anbui.recipely.feature.notification.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anbui.recipely.feature.notification.NotificationRoute

const val notificationRoute = "notification"

fun NavController.navigateToNotification(navOptions: NavOptions? = null) {
    this.navigate(notificationRoute)
}

fun NavGraphBuilder.notificationGraph(
    onBack: () -> Unit
) {
    composable(route = notificationRoute) {
        NotificationRoute(onBack = onBack)
    }
}