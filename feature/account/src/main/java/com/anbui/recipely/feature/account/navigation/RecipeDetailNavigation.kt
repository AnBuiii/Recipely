package com.anbui.recipely.feature.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.anbui.recipely.feature.account.account.AccountRoute
import com.anbui.recipely.feature.account.edit_profile.EditProfileRoute
import com.anbui.recipely.feature.account.setting.SettingRoute

fun NavController.onNavigateToAccountGraph(navOptions: NavOptions? = null) {
    this.navigate(AccountGraph.ROUTE, navOptions)
}

fun NavGraphBuilder.accountGraph(
    onBack: () -> Unit,
    onNavigateToOrderDetail: (String) -> Unit,
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
                onNavigateToOrderDetail = onNavigateToOrderDetail
            )
        }
        composable(route = AccountGraph.Setting.route) {
            SettingRoute(onBack = onBack, navigateToOnboard = onNavigateToOnboard)
        }
        composable(route = AccountGraph.EditProfile.route) {
            EditProfileRoute(onBack = onBack)
        }
    }

}