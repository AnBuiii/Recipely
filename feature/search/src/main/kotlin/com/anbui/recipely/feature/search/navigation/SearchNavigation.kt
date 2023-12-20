package com.anbui.recipely.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anbui.recipely.feature.search.SearchRoute

const val searchRoute = "search"
const val searchArg = "ingredientName"


fun NavController.navigateToSearch(ingredientName: String = "", navOptions: NavOptions? = null) {
    this.navigate("$searchRoute/$ingredientName", navOptions = navOptions)
}

fun NavGraphBuilder.searchGraph(
    onNavigateToRecipe: (String) -> Unit,
    onBack: () -> Unit
) {
    composable(route = "$searchRoute/{$searchArg}", arguments = listOf(
        navArgument(searchArg) {
            type = NavType.StringType
            defaultValue = ""
        }
    )
    ) {
        SearchRoute(
            onBack = onBack, onNavigateToRecipe = onNavigateToRecipe
        )
    }
}