package com.anbui.recipely.feature.recipe_detail.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.anbui.recipely.feature.recipe_detail.cooking_detail.CookingRoute
import com.anbui.recipely.feature.recipe_detail.recipe_detail.RecipeDetailRoute
import com.anbui.recipely.feature.recipe_detail.RecipeDetailViewModel

fun NavController.navigateToRecipeDetail(recipeId: String, navOptions: NavOptions? = null) {
    this.navigate("recipe_detail_graph/$recipeId", navOptions)
}

fun NavGraphBuilder.recipeDetailGraph(
    onBack: () -> Unit,
    navController: NavController,
) {
    navigation(
        route = RecipeDetailGraph.ROUTE,
        startDestination = RecipeDetailGraph.Home.route,
        arguments = listOf(
            navArgument(RecipeDetailGraph.ARG) {
                type = NavType.StringType
            }
        )
    ) {

        composable(route = RecipeDetailGraph.Home.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(RecipeDetailGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<RecipeDetailViewModel>(parentEntry)
            RecipeDetailRoute(
                onStartCooking = {
//                navController.navigate(Screen.CookingDetailScreen.route +
                    //                "/${recipe.id}" + "/${recipeDetailViewModel.servings.value}")
                    navController.navigate(RecipeDetailGraph.Cooking.route)
                },
                onBack = onBack,
                recipeDetailViewModel = parentViewModel
            )
        }
        composable(route = RecipeDetailGraph.Cooking.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(RecipeDetailGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<RecipeDetailViewModel>(parentEntry)
            CookingRoute(
                onBack = onBack,
                onBackToRecipe = {
//                navController.popBackStack(
//                    route = Screen.RecipeDetailScreen.route + "/${recipe.id}",
//                    false
//                )
                    navController.popBackStack()
                },
                recipeDetailViewModel = parentViewModel
            )
        }


    }

}