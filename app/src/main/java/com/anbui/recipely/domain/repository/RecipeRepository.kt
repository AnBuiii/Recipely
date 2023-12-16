package com.anbui.recipely.domain.repository


import com.anbui.recipely.core.database.relations.RecipeAndOwner
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeWithIngredient(recipeId: String): List<com.anbui.database.entities.relations.RecipeWithIngredient>

    fun findIngredientWithRecipeId(recipeId: String): Flow<List<RecipeAndOwner>>

    fun getAllRecipes(): Flow<List<com.anbui.model.Recipe>>

    fun getRecipesById(recipeId: String): Flow<com.anbui.model.Recipe>

    fun getDummyRecipe(): com.anbui.model.Recipe

    fun getFavouriteOfCurrentAccount(): Flow<List<com.anbui.model.Recipe>>

    fun getAllRecentOfCurrentAccount(): Flow<List<com.anbui.model.Recipe>>

    suspend fun searchRecipes(searchText: String): List<com.anbui.model.Recipe>

    suspend fun searchRecipesByIngredient(searchText: String): List<com.anbui.model.Recipe>

    suspend fun likeRecipe(recipeId: String, like: Boolean)

    suspend fun addRecentRecipe(recipeId: String)

    suspend fun searchIngredients(ingredientName: String): List<com.anbui.model.Ingredient>

    suspend fun getIngredientById(ingredientId: String): com.anbui.model.Ingredient?

    suspend fun createRecipe(
        title: String,
        imageUrl: String,
        description: String,
        servings: Int,
        ingredients: List<com.anbui.model.IngredientItem>,
        steps: List<com.anbui.model.Step>
    ): Boolean
}

