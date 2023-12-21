package com.anbui.recipely.feature.create_recipe.navigation

sealed class CreateRecipeGraph(val route: String){
    companion object {
        const val ROUTE = "create_recipe_graph"
    }
    data object Home: CreateRecipeGraph("create_recipe_graph_home")
    data object Ingredient: CreateRecipeGraph("ingredient")
    data object Instruction: CreateRecipeGraph("instruction")
}
