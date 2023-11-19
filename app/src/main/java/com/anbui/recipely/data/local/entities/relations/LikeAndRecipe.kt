package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.RecentEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.MediaType.Companion.toMediaType
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.Step

data class LikeAndRecipe(
    @Embedded
    val likeEntity: LikeEntity,

    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "_id",
        entity = RecipeEntity::class
    )
    val recipeAndOwner: RecipeAndOwner
) {
    fun toRecipe(): Recipe {
        return with(recipeAndOwner) {
            Recipe(
                id = recipe.id,
                title = recipe.title,
                imageUrl = recipe.imageUrl,
                description = recipe.description,
                isLike = true,
                cookTime = "${
                    steps.sumOf { step -> step.period.toDouble() }.toInt()
                } Min",
                servings = recipe.servings,
                totalCalories = ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.kcal
                }
                    .toFloat(),
                totalCarb = ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.carb
                }
                    .toFloat(),
                totalProtein = ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.protein
                }
                    .toFloat(),
                totalFat = ingredients.sumOf { ingredient ->
                    ingredient.crossRef.amount.toDouble() * ingredient.ingredient.fat
                }
                    .toFloat(),
                ownerId = owner.id,
                ownerName = owner.firstName + " " + owner.lastName,
                ownerAvatarUrl = owner.avatarUrl,
                ownerDescription = owner.bio,
                instructions = steps.map {
                    Step(
                        order = it.order,
                        instruction = it.instruction,
                        mediaUrl = it.mediaUrl,
                        type = it.mediaType.toMediaType(),
                        period = (it.period * 1000).toLong()
                    )
                },

                ingredients = ingredients.map { ingredient ->
                    IngredientItem(
                        imageUrl = ingredient.ingredient.imageUrl,
                        name = ingredient.ingredient.name,
                        ingredientId = ingredient.ingredient.id,
                        amount = ingredient.crossRef.amount,
                        unit = ingredient.ingredient.unit,
                        price = ingredient.ingredient.price
                    )
                }
            )
        }
    }
}
