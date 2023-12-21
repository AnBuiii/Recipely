package com.anbui.recipely.presentation.util

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")

    data object OrderDetailScreen : Screen("order_detail_screen")
    data object CameraScreen : Screen("camera_screen")
}
