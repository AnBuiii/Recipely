package com.anbui.recipely.data.repository

import android.util.Log
import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.RecentEntity
import com.anbui.recipely.data.local.entities.relations.RecipeAndOwner
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import com.anbui.recipely.data.local.entities.relations.toRecipe
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.repository.CurrentPreferences
import com.anbui.recipely.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val currentPreferences: CurrentPreferences,
) : RecipeRepository {

    override fun getFavouriteOfCurrentAccount(): Flow<List<Recipe>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            recipeDao.getFavouriteRecipeIds(id?:"").map {
                recipeDao.getRecipes(it).map { map ->
                    map.map {recipe ->
                        recipe.toRecipe(id)
                    }
                }.first()
            }.collect{
                emit(it)
            }
        }
    }

    override suspend fun getRecipeWithIngredient(recipeId: String): List<RecipeWithIngredient> {
        return recipeDao.getIngredientOfRecipe(recipeId = recipeId)
    }

    override fun findIngredientWithRecipeId(recipeId: String): Flow<List<RecipeAndOwner>> {
        return recipeDao.getAllRecipes()
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        val loggedId = currentPreferences.getLoggedId()
        return combine(recipeDao.getAllRecipes(), loggedId) { recipes, id ->
            recipes.map {
                it.toRecipe(id)
            }
        }
    }

    override fun getRecipesById(recipeId: String): Flow<Recipe> {
        val loggedId = currentPreferences.getLoggedId()
        return combine(recipeDao.getRecipe(recipeId = recipeId), loggedId) { recipe, id ->
            recipe.toRecipe(id)
        }
    }

    override fun getDummyRecipe(): Recipe {
        return Recipe(
            id = "",
            title = "",
            imageUrl = "",
            description = "This Healthy Taco Salad is the universal delight of taco night. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam odio libero, iaculis eget lacinia sollicitudin, auctor vitae purus. Suspendisse at semper risus. Nunc ingredientId scelerisque purus. Aliquam fringilla ultricies orci eget faucibus.",
            isLike = false,
            cookTime = "",
            servings = 0,
            totalCalories = 0.0f,
            totalCarb = 0.0f,
            totalProtein = 0.0f,
            totalFat = 0.0f,
            ownerId = "",
            ownerName = "",
            ownerAvatarUrl = "",
            ownerDescription = "",
            instructions = listOf(),
            ingredients = listOf()
        )
    }

    override suspend fun likeRecipe(recipeId: String, like: Boolean) {
        val loggedId = currentPreferences.getLoggedId()

        if (like) {
            val uuid = UUID.randomUUID()
            recipeDao.insertLike(
                LikeEntity(
                    id = uuid.toString(),
                    recipeId = recipeId,
                    accountId = loggedId.filterNotNull().first()
                )
            )
        } else {
            recipeDao.deleteLike(recipeId = recipeId, accountId = loggedId.filterNotNull().first())
        }
    }

    override suspend fun searchRecipes(searchText: String): List<Recipe> {
        val loggedId = currentPreferences.getLoggedId()
        return recipeDao.searchRecipe(searchText).map {
            it.toRecipe(loggedId.first())
        }
    }

    override suspend fun addRecentRecipe(recipeId: String) {
        val loggedId = currentPreferences.getLoggedId()
        if (recipeDao.getRecentByAccountAndRecipe(accountId = loggedId.first() ?: "", recipeId)
                .isNotEmpty()
        ) return
        val uuid = UUID.randomUUID()
        recipeDao.addRecent(
            RecentEntity(
                id = uuid.toString(),
                recipeId = recipeId,
                accountId = loggedId.filterNotNull().first()
            )
        )
    }

    override fun getAllRecentOfCurrentAccount(): Flow<List<Recipe>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            recipeDao.getAllRecent(id ?: "").map { map ->
                map.map {recipe ->
                    recipe.toRecipe(id)
                }
            }.collect {
                emit(it)
            }
        }
    }
}
