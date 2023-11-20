package com.anbui.recipely.domain.repository

import com.anbui.recipely.domain.models.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    suspend fun insertIngredient(ingredient: Ingredient)

    fun getIngredients(): Flow<List<Ingredient>>

//    suspend fun getIngredientById(id: String): Ingredient
}
