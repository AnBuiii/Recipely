package com.example.ingredient_detect.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.ingredient_detect.CameraRoute

const val cameraRoute = "camera"

fun NavController.navigateToCameraGraph(navOptions: NavOptions? = null) {
    this.navigate(cameraRoute, navOptions = navOptions)
}

fun NavGraphBuilder.cameraGraph(
    onNavigateToSearch: (String) -> Unit,
    onBack: () -> Unit
) {
    composable(route = cameraRoute)
    {
        CameraRoute(onBack = onBack, navigateToSearch = onNavigateToSearch)
    }
}