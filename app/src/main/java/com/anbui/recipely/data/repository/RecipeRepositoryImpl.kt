package com.anbui.recipely.data.repository

import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.relations.IngredientWithAmount
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import com.anbui.recipely.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun getRecipeWithIngredient(recipeId: String): List<RecipeWithIngredient> {
        return recipeDao.getIngredientOfRecipe(recipeId = recipeId)
    }

    override suspend fun findIngredientWithRecipeId(recipeId: String): Map<RecipeEntity, List<IngredientWithAmount>> {
        return recipeDao.findRecipeWithIngredient()
    }
}