package com.anbui.recipely.core.data.repository

import com.anbui.recipely.core.model.Ingredient
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.core.model.Step
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getAllRecipes(): Flow<List<Recipe>>

    fun getRecipesById(recipeId: String): Flow<Recipe>

    fun getDummyRecipe(): Recipe

    fun getFavouriteOfCurrentAccount(): Flow<List<Recipe>>

    fun getAllRecentOfCurrentAccount(): Flow<List<Recipe>>

    fun getAllRecipeOfCurrentAccount(): Flow<List<Recipe>>

    suspend fun searchRecipes(searchText: String): List<Recipe>

    suspend fun searchRecipesByIngredient(searchText: String): List<Recipe>

    suspend fun likeRecipe(recipeId: String, like: Boolean)

    suspend fun addRecentRecipe(recipeId: String)

    suspend fun searchIngredients(ingredientName: String): List<Ingredient>

    suspend fun getIngredientById(ingredientId: String): Ingredient?

    suspend fun deleteRecipe(recipeId: String)

    suspend fun createRecipe(
        title: String,
        imageUrl: String,
        description: String,
        servings: Int,
        ingredients: List<IngredientItem>,
        steps: List<Step>
    ): Boolean
}