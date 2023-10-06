package com.anbui.recipely.domain.repository

import com.anbui.recipely.data.local.dao.RecipeAndOwner
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import com.anbui.recipely.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeWithIngredient(recipe: String): List<RecipeWithIngredient>
    fun findIngredientWithRecipeId(recipeId: String): Flow<List<RecipeAndOwner>>

    fun getAllRecipes(): Flow<List<Recipe>>
}
