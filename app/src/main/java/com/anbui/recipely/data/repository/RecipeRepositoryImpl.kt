package com.anbui.recipely.data.repository

import android.util.Log
import com.anbui.database.AccountDao
import com.anbui.database.RecipeDao
import com.anbui.database.LikeEntity
import com.anbui.database.RecentEntity
import com.anbui.database.RecipeEntity
import com.anbui.database.StepEntity
import com.anbui.database.entities.relations.RecipeAndOwner
import com.anbui.database.entities.relations.RecipeIngredientCrossRef
import com.anbui.database.entities.relations.RecipeWithIngredient
import com.anbui.database.entities.relations.toRecipe
import com.anbui.database.entities.relations.toRecipes
import com.anbui.recipely.domain.repository.CurrentPreferences
import com.anbui.recipely.domain.repository.NotificationRepository
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
    private val recipeDao: com.anbui.database.RecipeDao,
    private val accountDao: com.anbui.database.AccountDao,
    private val currentPreferences: CurrentPreferences,
    private val notificationRepository: NotificationRepository
) : RecipeRepository {

    override fun getFavouriteOfCurrentAccount(): Flow<List<com.anbui.model.Recipe>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            recipeDao.getFavouriteRecipes(id ?: "").map { map ->
                map.map { recipe ->
                    recipe.toRecipe()
                }
            }.collect {
                emit(it)
            }
        }
    }

    override suspend fun getRecipeWithIngredient(recipeId: String): List<com.anbui.database.entities.relations.RecipeWithIngredient> {
        return recipeDao.getIngredientOfRecipe(recipeId = recipeId)
    }

    override fun findIngredientWithRecipeId(recipeId: String): Flow<List<com.anbui.database.entities.relations.RecipeAndOwner>> {
        return recipeDao.getAllRecipes()
    }

    override fun getAllRecipes(): Flow<List<com.anbui.model.Recipe>> {
        val loggedId = currentPreferences.getLoggedId()
        return combine(recipeDao.getAllRecipes(), loggedId) { recipes, id ->
            recipes.map {
                it.toRecipe(id)
            }
        }
    }

    override fun getRecipesById(recipeId: String): Flow<com.anbui.model.Recipe> {
        val loggedId = currentPreferences.getLoggedId()
        return combine(recipeDao.getRecipe(recipeId = recipeId), loggedId) { recipe, id ->
            recipe.toRecipe(id)
        }
    }

    override fun getDummyRecipe(): com.anbui.model.Recipe {
        return com.anbui.model.Recipe(
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
        val loggedId = currentPreferences.getLoggedId().first() ?: return
        val account = accountDao.getAccountById(loggedId).first()

        if (like) {
            val uuid = UUID.randomUUID()
            recipeDao.insertLike(
                com.anbui.database.LikeEntity(
                    id = uuid.toString(),
                    recipeId = recipeId,
                    accountId = loggedId
                )
            )
            com.anbui.model.Notification(
                id = UUID.randomUUID().toString(),
                userId = loggedId,
                notificationType = com.anbui.model.NotificationType.Like,
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

    override suspend fun searchIngredients(ingredientName: String): List<com.anbui.model.Ingredient> {
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

    override suspend fun searchRecipesByIngredient(searchText: String): List<com.anbui.model.Recipe> {
        val loggedId = currentPreferences.getLoggedId()
        return recipeDao.searchRecipesByIngredient(searchText)?.toRecipes(loggedId.first()) ?: emptyList()
    }

    override suspend fun getIngredientById(ingredientId: String): com.anbui.model.Ingredient? {
        return recipeDao.getIngredientById(ingredientId)?.toIngredient()
    }

    override suspend fun createRecipe(
        title: String,
        imageUrl: String,
        description: String,
        servings: Int,
        ingredients: List<com.anbui.model.IngredientItem>,
        steps: List<com.anbui.model.Step>
    ): Boolean {
        val loggedId = currentPreferences.getLoggedId().first() ?: ""
        val recipeId = UUID.randomUUID().toString()
        com.anbui.database.RecipeEntity(
            id = recipeId,
            title = title,
            imageUrl = imageUrl,
            description = description,
            servings = servings,
            owner = loggedId
        ).let { recipeDao.insertRecipe(it) }
        ingredients.map {
            com.anbui.database.entities.relations.RecipeIngredientCrossRef(
                id = UUID.randomUUID().toString(),
                recipeId = recipeId,
                ingredientId = it.ingredientId,
                amount = it.amount
            )
        }.let { recipeDao.insertContains(it) }
        steps.mapIndexed { idx, step ->
            com.anbui.database.StepEntity(
                id = step.id,
                period = step.period.toFloat(),
                recipeId = recipeId,
                instruction = step.instruction,
                mediaUrl = step.mediaUrl ?: "",
                mediaType = step.type.type,
                order = idx + 1
            )
        }.let { recipeDao.insertSteps(it) }
        return true
    }

    override suspend fun searchRecipes(searchText: String): List<com.anbui.model.Recipe> {
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
            com.anbui.database.RecentEntity(
                id = uuid.toString(),
                recipeId = recipeId,
                accountId = loggedId.filterNotNull().first()
            )
        )
    }

    override fun getAllRecentOfCurrentAccount(): Flow<List<com.anbui.model.Recipe>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            recipeDao.getAllRecent(id ?: "").map { map ->
                map.map { recipe ->
                    recipe.toRecipe()
                }
            }.collect {
                emit(it)
            }
        }
    }
}
