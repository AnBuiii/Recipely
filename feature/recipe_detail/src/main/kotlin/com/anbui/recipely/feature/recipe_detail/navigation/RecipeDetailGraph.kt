package com.anbui.recipely.feature.recipe_detail.navigation

sealed class RecipeDetailGraph(val route: String){
    companion object {
        const val ARG = "recipeId"
        const val ROUTE = "recipe_detail_graph/{$ARG}"
    }
    data object Home: RecipeDetailGraph("recipe_detail_graph_home")
    data object Cooking: RecipeDetailGraph("cooking")
}
