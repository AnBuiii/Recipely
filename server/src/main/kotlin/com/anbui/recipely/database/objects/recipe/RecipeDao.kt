package com.anbui.recipely.database.objects.recipe

interface RecipeDao {
    suspend fun getAllRecipes(): List<RecipeEntity>
    suspend fun addRecipe(): RecipeEntity?
}