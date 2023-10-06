package com.anbui.recipely.data.repository

import com.anbui.recipely.data.local.dao.RecipeAndOwner
import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.MediaType.Companion.toMediaType
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.Step
import com.anbui.recipely.domain.models.UnitType.Companion.toUnitType
import com.anbui.recipely.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun getRecipeWithIngredient(recipeId: String): List<RecipeWithIngredient> {
        return recipeDao.getIngredientOfRecipe(recipeId = recipeId)
    }

    override fun findIngredientWithRecipeId(recipeId: String): Flow<List<RecipeAndOwner>> {
        return recipeDao.findRecipeWithIngredient()
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        val recipeUnformatted = recipeDao.findRecipeWithIngredient()

        return recipeUnformatted.map { map ->
            map.map { it ->
                Recipe(
                    id = it.recipe.id,
                    title = it.recipe.title,
                    imageUrl = it.recipe.imageUrl,
                    description = it.recipe.description,
                    isLike = it.likes.any { like -> like.accountId == "anbui" },
                    cookTime = "${it.steps.sumOf { step -> step.period.toDouble() }.toInt()} Min",
                    servings = it.recipe.servings,
                    totalCalories = it.ingredients.sumOf { ingredient -> ingredient.crossRef.amount * ingredient.ingredient.kcal }
                        .toFloat(),
                    totalCarb = it.ingredients.sumOf { ingredient -> ingredient.crossRef.amount * ingredient.ingredient.carb }
                        .toFloat(),
                    totalProtein = it.ingredients.sumOf { ingredient -> ingredient.crossRef.amount * ingredient.ingredient.protein }
                        .toFloat(),
                    totalFat = it.ingredients.sumOf { ingredient -> ingredient.crossRef.amount * ingredient.ingredient.fat }
                        .toFloat(),
                    ownerId = it.owner.id,
                    ownerName = it.owner.firstName + " " + it.owner.lastName,
                    ownerAvatarUrl = it.owner.avatarUrl,
                    ownerDescription = it.owner.bio,
                    instructions = it.steps.map {
                        Step(
                            order = it.order,
                            instruction = it.instruction,
                            mediaUrl = it.mediaUrl,
                            type = it.mediaType.toMediaType(),
                            period = (it.period * 1000).toLong()
                        )
                    },

                    ingredients = it.ingredients.map { ingredient ->
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
//
//    override suspend fun get(recipeId: String): Hmm {
//        return recipeDao.get(recipeId = recipeId)
//    }
}
