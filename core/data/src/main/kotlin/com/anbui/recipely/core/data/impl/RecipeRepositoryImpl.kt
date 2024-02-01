package com.anbui.recipely.core.data.impl

import android.util.Log
import com.anbui.recipely.core.data.repository.NotificationRepository
import com.anbui.recipely.core.data.repository.RecipeRepository
import com.anbui.recipely.core.database.dao.AccountDao
import com.anbui.recipely.core.database.dao.RecipeDao
import com.anbui.recipely.core.database.entities.LikeEntity
import com.anbui.recipely.core.database.entities.RecentEntity
import com.anbui.recipely.core.database.entities.RecipeEntity
import com.anbui.recipely.core.database.entities.RecipeIngredientCrossRef
import com.anbui.recipely.core.database.entities.StepEntity
import com.anbui.recipely.core.database.entities.toIngredient
import com.anbui.recipely.core.database.relations.toRecipe
import com.anbui.recipely.core.database.relations.toRecipes
import com.anbui.recipely.core.datastore.RecipelyPreferencesDataSource
import com.anbui.recipely.core.model.Ingredient
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Notification
import com.anbui.recipely.core.model.NotificationType
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.core.model.Step
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val accountDao: AccountDao,
    private val preferencesDataSource: RecipelyPreferencesDataSource,
    private val notificationRepository: NotificationRepository
) : RecipeRepository {

    override fun getFavouriteOfCurrentAccount(): Flow<List<Recipe>> {
        return flow {
            val id = preferencesDataSource.loggedId.first()
            recipeDao.getFavouriteRecipes(id).map { map ->
                map.map { recipe ->
                    recipe.toRecipe()
                }
            }.collect {
                emit(it)
            }
        }
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return combine(recipeDao.getAllRecipes(), preferencesDataSource.loggedId) { recipes, id ->
            recipes.map {
                it.toRecipe(id)
            }
        }
    }

    override fun getRecipesById(recipeId: String): Flow<Recipe> {
        return combine(
            recipeDao.getRecipe(recipeId = recipeId),
            preferencesDataSource.loggedId
        ) { recipe, id ->
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
        val loggedId = preferencesDataSource.loggedId.first()
        val account = accountDao.getAccountById(loggedId).first()

        if (like) {
            val uuid = UUID.randomUUID()
            recipeDao.insertLike(
                LikeEntity(
                    id = uuid.toString(),
                    recipeId = recipeId,
                    accountId = loggedId
                )
            )
            Notification(
                id = UUID.randomUUID().toString(),
                userId = loggedId,
                notificationType = NotificationType.Like,
                message = "${account.firstName} like your recipe",
                isRead = false,
                imageUrl = null
            ).let {
                notificationRepository.insertNotification(it)
            }
        } else {
            recipeDao.deleteLike(recipeId = recipeId, accountId = loggedId)
        }

    }

    override suspend fun searchIngredients(ingredientName: String): List<Ingredient> {
        val a = ingredientName.replaceFirstChar {
            it.uppercase()
        }
        Log.d("Classification search", a)
        val result = recipeDao.searchIngredient(
            a
        )
        Log.d("Classification search", result.toString())

        return result.map {
            val b = it.toIngredient()
            Log.d("Classification search", b.toString())
            b
        }
    }

    override suspend fun searchRecipesByIngredient(searchText: String): List<Recipe> {
        val loggedId = preferencesDataSource.loggedId.first()
        return recipeDao.searchRecipesByIngredient(searchText)?.toRecipes(loggedId)
            ?: emptyList()
    }

    override suspend fun getIngredientById(ingredientId: String): Ingredient? {
        return recipeDao.getIngredientById(ingredientId)?.toIngredient()
    }

    override suspend fun createRecipe(
        title: String,
        imageUrl: String,
        description: String,
        servings: Int,
        ingredients: List<IngredientItem>,
        steps: List<Step>
    ): Boolean {
        val loggedId = preferencesDataSource.loggedId.first()
        val recipeId = UUID.randomUUID().toString()
        RecipeEntity(
            id = recipeId,
            title = title,
            imageUrl = imageUrl,
            description = description,
            servings = servings,
            owner = loggedId
        ).let { recipeDao.insertRecipe(it) }
        ingredients.map {
            RecipeIngredientCrossRef(
                id = UUID.randomUUID().toString(),
                recipeId = recipeId,
                ingredientId = it.ingredientId,
                amount = it.amount
            )
        }.let { recipeDao.insertContains(it) }
        steps.mapIndexed { idx, step ->
            StepEntity(
                id = step.id,
                period = step.period.toFloat(),
                recipeId = recipeId,
                instruction = step.instruction,
                mediaUrl = step.mediaUrl ?: "",
                mediaType = step.type.toString(),
                order = idx + 1
            )
        }.let { recipeDao.insertSteps(it) }
        return true
    }

    override suspend fun searchRecipes(searchText: String): List<Recipe> {
        val loggedId = preferencesDataSource.loggedId.first()
        return recipeDao.searchRecipe(searchText).map {
            it.toRecipe(loggedId)
        }
    }

    override suspend fun addRecentRecipe(recipeId: String) {
        val loggedId = preferencesDataSource.loggedId.first()
        if (recipeDao.getRecentByAccountAndRecipe(accountId = loggedId, recipeId)
                .isNotEmpty()
        ) return
        val uuid = UUID.randomUUID()
        recipeDao.addRecent(
            RecentEntity(
                id = uuid.toString(),
                recipeId = recipeId,
                accountId = loggedId
            )
        )
    }

    override fun getAllRecentOfCurrentAccount(): Flow<List<Recipe>> {
        return flow {
            val id = preferencesDataSource.loggedId.first()
            recipeDao.getAllRecent(id).map { map ->
                map.map { recipe ->
                    recipe.toRecipe()
                }
            }.collect {
                emit(it)
            }
        }
    }

    override fun getAllRecipeOfCurrentAccount(): Flow<List<Recipe>> {
        return flow {
            val id = preferencesDataSource.loggedId.first()
            recipeDao.getRecipeByAccountId(id).map { map ->
                map.map { recipe ->
                    recipe.toRecipe(id)
                }
            }.collect {
                emit(it)
            }
        }
    }

    override suspend fun deleteRecipe(recipeId: String) {
        recipeDao.deleteRecipe(recipeId)
    }
}
