package com.anbui.recipely.presentation.util

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anbui.recipely.feature.create_recipe.navigation.createRecipeGraph
import com.anbui.recipely.feature.notification.navigation.notificationGraph
import com.anbui.recipely.feature.onboard.navigation.OnboardGraph
import com.anbui.recipely.feature.onboard.navigation.onBoardGraph
import com.anbui.recipely.feature.recipe_detail.navigation.recipeDetailGraph
import com.anbui.recipely.feature.search.navigation.searchGraph
import com.anbui.recipely.presentation.cart_order.address.AddressScreen
import com.anbui.recipely.presentation.cart_order.cart.CartScreen
import com.anbui.recipely.presentation.cart_order.order_detail.OrderDetailScreen
import com.anbui.recipely.presentation.main_screen.account.AccountScreen
import com.anbui.recipely.presentation.main_screen.home.HomeScreen
import com.anbui.recipely.presentation.other_feature.camera.CameraScreen
import com.anbui.recipely.presentation.other_feature.edit_profile.EditProfileScreen
import com.anbui.recipely.presentation.other_feature.setting.SettingScreen

@ExperimentalAnimationApi
@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = OnboardGraph.ROUTE) {
        onBoardGraph(
            onHome = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(OnboardGraph.Splash.route) {
                        inclusive = true
                    }
                }
            },
            navController = navController
        )

        notificationGraph(
            onBack = navController::navigateUp
        )

        searchGraph(
            onBack = navController::navigateUp,
            onNavigateToRecipe = {
                navController.navigate(
                    Screen.RecipeDetailScreen.route + "/$it"
                )
            }
        )

        recipeDetailGraph(
            onBack = navController::popBackStack,
            navController = navController
        )

        createRecipeGraph(
            onBack = navController::popBackStack,
            navController = navController
        )

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.AccountScreen.route) {
            AccountScreen(navController = navController)
        }

        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }

        composable(Screen.CartScreen.route) {
            CartScreen(navController = navController)
        }

        composable(Screen.AddressScreen.route) {
            AddressScreen(navController = navController)
        }
        composable(Screen.SettingScreen.route) {
            SettingScreen(navController = navController)
        }
        composable(
            "${Screen.OrderDetailScreen.route}/{orderId}", arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                },
            )
        ) {
            OrderDetailScreen(navController = navController)
        }
        composable(Screen.CameraScreen.route) {
            CameraScreen(navController = navController)
        }
    }
}
