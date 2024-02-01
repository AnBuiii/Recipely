package com.anbui.recipely.core.testing

import com.anbui.recipely.core.data.repository.RecipeRepository
import com.anbui.recipely.core.model.Ingredient
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.core.model.Step
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRecipeRepository @Inject constructor() : RecipeRepository {

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return flow { }

    }

    override fun getRecipesById(recipeId: String): Flow<Recipe> {
        return flow { }

    }

    override fun getDummyRecipe(): Recipe {
        return Recipe(
            id = "lectus",
            title = "nostrum",
            imageUrl = "http://www.bing.com/search?q=ius",
            description = "nominavi",
            isLike = false,
            cookTime = "nullam",
            servings = 7686,
            totalCalories = 12.13f,
            totalCarb = 14.15f,
            totalProtein = 16.17f,
            totalFat = 18.19f,
            ownerId = "porro",
            ownerName = "Spencer Bolton",
            ownerAvatarUrl = "https://www.google.com/#q=vel",
            ownerDescription = "convenire",
            instructions = listOf(),
            ingredients = listOf()
        )

    }

    override fun getFavouriteOfCurrentAccount(): Flow<List<Recipe>> {
        return flow { }

    }

    override fun getAllRecentOfCurrentAccount(): Flow<List<Recipe>> {
        return flow { }

    }

    override fun getAllRecipeOfCurrentAccount(): Flow<List<Recipe>> {
        return flow { }

    }

    override suspend fun searchRecipes(searchText: String): List<Recipe> {
        return emptyList()

    }

    override suspend fun searchRecipesByIngredient(searchText: String): List<Recipe> {
        return emptyList()

    }

    override suspend fun likeRecipe(recipeId: String, like: Boolean) {
        //

    }

    override suspend fun addRecentRecipe(recipeId: String) {
        //


    }

    override suspend fun searchIngredients(ingredientName: String): List<Ingredient> {
        return emptyList()

    }

    override suspend fun getIngredientById(ingredientId: String): Ingredient? {
        return null

    }

    override suspend fun deleteRecipe(recipeId: String) {
        //

    }

    override suspend fun createRecipe(
        title: String,
        imageUrl: String,
        description: String,
        servings: Int,
        ingredients: List<IngredientItem>,
        steps: List<Step>
    ): Boolean {
        return true

    }
}