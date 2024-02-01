package com.anbui.recipely.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anbui.recipely.home.HomeRoute

const val homeRoute = "recipely_home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions = navOptions)
}

fun NavGraphBuilder.homeGraph(
    navigateToRecipeDetail: (String) -> Unit,
    navigateToCart: () -> Unit,
) {
    composable(route = homeRoute) {
        HomeRoute(navigateToRecipeDetail = navigateToRecipeDetail, navigateToCart = navigateToCart)
    }
}