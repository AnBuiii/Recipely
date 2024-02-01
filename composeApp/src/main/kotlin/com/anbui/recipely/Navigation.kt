package com.anbui.recipely

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.anbui.recipely.feature.account.navigation.accountGraph
import com.anbui.recipely.feature.cart.navigation.cartGraph
import com.anbui.recipely.feature.cart.navigation.onNavigateToCartGraph
import com.anbui.recipely.feature.create_recipe.navigation.createRecipeGraph
import com.anbui.recipely.feature.notification.navigation.notificationGraph
import com.anbui.recipely.feature.onboard.navigation.OnboardGraph
import com.anbui.recipely.feature.onboard.navigation.navigateToOnboard
import com.anbui.recipely.feature.onboard.navigation.onBoardGraph
import com.anbui.recipely.feature.recipe_detail.navigation.navigateToRecipeDetail
import com.anbui.recipely.feature.recipe_detail.navigation.recipeDetailGraph
import com.anbui.recipely.feature.search.navigation.navigateToSearch
import com.anbui.recipely.feature.search.navigation.searchGraph
import com.anbui.recipely.home.navigation.homeGraph
import com.anbui.recipely.home.navigation.navigateToHome
import com.example.ingredient_detect.navigation.cameraGraph
import com.example.ingredient_detect.navigation.cameraRoute

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = OnboardGraph.ROUTE) {
        onBoardGraph(
            onHome = {
                navController.navigateToHome(
                    navOptions {
                        popUpTo(OnboardGraph.Splash.route) {
                            inclusive = true
                        }
                    }
                )
            },
            navController = navController
        )

        notificationGraph(
            onBack = navController::navigateUp
        )

        searchGraph(
            onBack = navController::navigateUp,
            onNavigateToRecipe = navController::navigateToRecipeDetail
        )

        recipeDetailGraph(
            onBack = navController::popBackStack,
            navController = navController
        )

        createRecipeGraph(
            onBack = navController::popBackStack,
            navController = navController
        )

        cartGraph(
            onBack = navController::popBackStack,
            navController = navController
        )

        accountGraph(
            onBack = navController::popBackStack,
            onNavigateToOnboard = {
                navController.navigateToOnboard()
            },
            onNavigateToRecipe = navController::navigateToRecipeDetail,
            navController = navController
        )

        cameraGraph(
            onBack = navController::popBackStack,
            onNavigateToSearch = {
                navController.navigateToSearch(
                    ingredientName = it,
                    navOptions = navOptions {
                        popUpTo(cameraRoute) {
                            inclusive = true
                        }
                    }
                )
            }
        )

        homeGraph(
            navigateToRecipeDetail = navController::navigateToRecipeDetail,
            navigateToCart = {
                navController.onNavigateToCartGraph(
                    navOptions = navOptions {
                        launchSingleTop = true
                    }
                )
            },
        )
    }
}


