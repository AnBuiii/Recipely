package com.anbui.recipely.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.core.database.entities.LikeEntity
import com.anbui.recipely.core.database.entities.RecipeEntity
import com.anbui.recipely.core.database.entities.toStep
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Recipe

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
                    it.toStep()
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
