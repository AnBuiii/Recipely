package com.anbui.recipely.data.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.relations.RecipeAndOwner
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import com.anbui.recipely.dataStore
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.MediaType.Companion.toMediaType
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.Step
import com.anbui.recipely.domain.models.UnitType.Companion.toUnitType
import com.anbui.recipely.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID


class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao,
    private val context: Context
) : RecipeRepository {
    override suspend fun getRecipeWithIngredient(recipeId: String): List<RecipeWithIngredient> {
        return recipeDao.getIngredientOfRecipe(recipeId = recipeId)
    }

    override fun findIngredientWithRecipeId(recipeId: String): Flow<List<RecipeAndOwner>> {
        return recipeDao.getAllRecipes()
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        val logged = stringPreferencesKey("logged_id")
        val loggedId = context.dataStore.data.map { preferences ->
            preferences[logged] ?: ""
        }

        return recipeDao.getAllRecipes().map { map ->
            map.map { recipe ->
                Recipe(
                    id = recipe.recipe.id.toString(),
                    title = recipe.recipe.title,
                    imageUrl = recipe.recipe.imageUrl,
                    description = recipe.recipe.description,
                    isLike = recipe.likes.any { like -> like.accountId == loggedId.first() },
                    cookTime = "${
                        recipe.steps.sumOf { step -> step.period.toDouble() }.toInt()
                    } Min",
                    servings = recipe.recipe.servings,
                    totalCalories = recipe.ingredients.sumOf { ingredient ->
                        ingredient.crossRef.amount.toDouble() * ingredient.ingredient.kcal
                    }
                        .toFloat(),
                    totalCarb = recipe.ingredients.sumOf { ingredient ->
                        ingredient.crossRef.amount.toDouble() * ingredient.ingredient.carb
                    }
                        .toFloat(),
                    totalProtein = recipe.ingredients.sumOf { ingredient ->
                        ingredient.crossRef.amount.toDouble() * ingredient.ingredient.protein
                    }
                        .toFloat(),
                    totalFat = recipe.ingredients.sumOf { ingredient ->
                        ingredient.crossRef.amount.toDouble() * ingredient.ingredient.fat
                    }
                        .toFloat(),
                    ownerId = recipe.owner.id.toString(),
                    ownerName = recipe.owner.firstName + " " + recipe.owner.lastName,
                    ownerAvatarUrl = recipe.owner.avatarUrl,
                    ownerDescription = recipe.owner.bio,
                    instructions = recipe.steps.map {
                        Step(
                            order = it.order,
                            instruction = it.instruction,
                            mediaUrl = it.mediaUrl,
                            type = it.mediaType.toMediaType(),
                            period = (it.period * 1000).toLong()
                        )
                    },

                    ingredients = recipe.ingredients.map { ingredient ->
                        IngredientItem(
                            imageUrl = ingredient.ingredient.imageUrl,
                            name = ingredient.ingredient.name,
                            ingredientId = ingredient.ingredient.id,
                            amount = ingredient.crossRef.amount.toFloat(),
                            unit = ingredient.ingredient.unit.toUnitType(),
                            price = ingredient.ingredient.price
                        )
                    }
                )
            }
        }
    }

    override fun getRecipesById(recipeId: String): Flow<Recipe> {
        val logged = stringPreferencesKey("logged_id")
        val loggedId = context.dataStore.data.map { preferences ->
            preferences[logged] ?: ""
        }

        return recipeDao.getRecipe(recipeId = recipeId).map { recipe ->
            Recipe(
                id = recipe.recipe.id.toString(),
                title = recipe.recipe.title,
                imageUrl = recipe.recipe.imageUrl,
                description = recipe.recipe.description,
                isLike = recipe.likes.any { like -> like.accountId == loggedId.first() },
                cookTime = "${
                    recipe.steps.sumOf { step -> step.period.toDouble() }.toInt()
                } Min",
                servings = recipe.recipe.servings,
                totalCalories = recipe.ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.kcal
                }
                    .toFloat(),
                totalCarb = recipe.ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.carb
                }
                    .toFloat(),
                totalProtein = recipe.ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.protein
                }
                    .toFloat(),
                totalFat = recipe.ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.fat
                }
                    .toFloat(),
                ownerId = recipe.owner.id,
                ownerName = recipe.owner.firstName + " " + recipe.owner.lastName,
                ownerAvatarUrl = recipe.owner.avatarUrl,
                ownerDescription = recipe.owner.bio,
                instructions = recipe.steps.map {
                    Step(
                        order = it.order,
                        instruction = it.instruction,
                        mediaUrl = it.mediaUrl,
                        type = it.mediaType.toMediaType(),
                        period = (it.period * 1000).toLong()
                    )
                },

                ingredients = recipe.ingredients.map { ingredient ->
                    IngredientItem(
                        imageUrl = ingredient.ingredient.imageUrl,
                        name = ingredient.ingredient.name,
                        ingredientId = ingredient.ingredient.id,
                        amount = ingredient.crossRef.amount.toFloat(),
                        unit = ingredient.ingredient.unit.toUnitType(),
                        price = ingredient.ingredient.price
                    )
                }
            )
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
        val logged = stringPreferencesKey("logged_id")
        val loggedId = context.dataStore.data.map { preferences ->
            preferences[logged] ?: ""
        }

        if (like) {
            val uuid = UUID.randomUUID()
            recipeDao.insertLike(
                LikeEntity(
                    id = uuid.toString(),
                    recipeId = recipeId,
                    accountId = loggedId.first()
                )
            )
        } else {
            recipeDao.deleteLike(recipeId = recipeId, accountId = loggedId.first())
        }
    }

}
