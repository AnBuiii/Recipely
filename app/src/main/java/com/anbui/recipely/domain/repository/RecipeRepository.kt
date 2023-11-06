package com.anbui.recipely.domain.repository


import com.anbui.recipely.data.local.entities.relations.RecipeAndOwner
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import com.anbui.recipely.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeWithIngredient(recipeId: String): List<RecipeWithIngredient>

    fun findIngredientWithRecipeId(recipeId: String): Flow<List<RecipeAndOwner>>

    fun getAllRecipes(): Flow<List<Recipe>>

    fun getRecipesById(recipeId: String): Flow<Recipe>

    fun getDummyRecipe(): Recipe

    fun getFavouriteOfAccountId(accountId: String): Flow<List<Recipe>>

    suspend fun likeRecipe(recipeId: String, like: Boolean)
}

