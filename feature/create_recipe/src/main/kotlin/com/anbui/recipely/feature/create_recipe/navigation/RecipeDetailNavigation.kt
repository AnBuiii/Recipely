package com.anbui.recipely.feature.create_recipe.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.anbui.recipely.feature.create_recipe.CreateRecipeRoute
import com.anbui.recipely.feature.create_recipe.CreateRecipeViewModel
import com.anbui.recipely.feature.create_recipe.add_ingredient.AddIngredientRoute
import com.anbui.recipely.feature.create_recipe.add_instruction.AddInstructionRoute

fun NavController.navigateToCreateRecipe(navOptions: NavOptions? = null) {
    this.navigate(CreateRecipeGraph.ROUTE, navOptions)
}

fun NavGraphBuilder.createRecipeGraph(
    onBack: () -> Unit,
    navController: NavController,
) {
    navigation(
        route = CreateRecipeGraph.ROUTE,
        startDestination = CreateRecipeGraph.Home.route,
    ) {

        composable(route = CreateRecipeGraph.Home.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CreateRecipeGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<CreateRecipeViewModel>(parentEntry)
            CreateRecipeRoute(
                onBack = onBack,
                onNavigateToIngredient = {
                    navController.navigate(CreateRecipeGraph.Ingredient.route)
                },
                onNavigateToInstruction = {
                    navController.navigate(CreateRecipeGraph.Instruction.route)
                },
                createRecipeViewModel = parentViewModel
            )
        }
        composable(route = CreateRecipeGraph.Ingredient.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CreateRecipeGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<CreateRecipeViewModel>(parentEntry)
            AddIngredientRoute(onBack = onBack, createRecipeViewModel = parentViewModel)
        }

        composable(route = CreateRecipeGraph.Instruction.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CreateRecipeGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<CreateRecipeViewModel>(parentEntry)
            AddInstructionRoute(onBack = onBack, createRecipeViewModel = parentViewModel)
        }
    }

}