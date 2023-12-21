package com.anbui.recipely.feature.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.anbui.recipely.feature.account.account.AccountRoute
import com.anbui.recipely.feature.account.edit_profile.EditProfileRoute
import com.anbui.recipely.feature.account.my_order.MyOrderRoute
import com.anbui.recipely.feature.account.my_recipe.MyRecipeRoute
import com.anbui.recipely.feature.account.order_detail.OrderDetailRoute
import com.anbui.recipely.feature.account.setting.SettingRoute

fun NavController.onNavigateToAccountGraph(navOptions: NavOptions? = null) {
    this.navigate(AccountGraph.ROUTE, navOptions)
}

fun NavGraphBuilder.accountGraph(
    onBack: () -> Unit,
    onNavigateToRecipe: (String) -> Unit,
    onNavigateToOnboard: () -> Unit,
    navController: NavController,
) {
    navigation(
        route = AccountGraph.ROUTE,
        startDestination = AccountGraph.Home.route,
    ) {

        composable(route = AccountGraph.Home.route) { backStackEntry ->
            AccountRoute(
                onBack = onBack,
                onNavigateToSettingScreen = {
                    navController.navigate(AccountGraph.Setting.route) {
                        launchSingleTop = true
                    }
                },
                onNavigateToEditProfileScreen = {
                    navController.navigate(AccountGraph.EditProfile.route) {
                        launchSingleTop = true
                    }
                },
                onNavigateToOrderDetail = {
                    navController.navigate("order_detail/$it") {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = AccountGraph.Setting.route) {
            SettingRoute(
                onBack = onBack,
                navigateToOnboard = onNavigateToOnboard,
                navigateToMyOrder = {
                    navController.navigate(AccountGraph.MyOrder.route) {
                        launchSingleTop = true
                    }
                },
                navigateToMyRecipe = {
                    navController.navigate(AccountGraph.MyRecipe.route) {
                        launchSingleTop = true
                    }
                })
        }
        composable(route = AccountGraph.EditProfile.route) {
            EditProfileRoute(onBack = onBack)
        }
        composable(route = AccountGraph.MyRecipe.route) {
            MyRecipeRoute(onBack = onBack, onNavigateToRecipe = onNavigateToRecipe)
        }
        composable(route = AccountGraph.MyOrder.route) {
            MyOrderRoute(
                onBack = onBack,
                onNavigateToOrder = {
                    navController.navigate("order_detail/$it") {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = AccountGraph.OrderDetail.route,
            arguments = listOf(
                navArgument(AccountGraph.ORDER_ARG) {
                    type = NavType.StringType
                },
            )
        ) {
            OrderDetailRoute(onBack = onBack)
        }
    }
}