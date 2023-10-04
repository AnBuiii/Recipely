package com.anbui.recipely.domain.repository

import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.relations.IngredientWithAmount
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient

interface RecipeRepository {
    suspend fun getRecipeWithIngredient(recipe: String): List<RecipeWithIngredient>
    suspend fun findIngredientWithRecipeId(recipeId: String) : Map<RecipeEntity, List<IngredientWithAmount>>

}