package com.anbui.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.database.entities.AccountEntity
import com.anbui.database.entities.LikeEntity
import com.anbui.database.entities.RecipeEntity
import com.anbui.database.entities.StepEntity
import com.anbui.model.IngredientItem
import com.anbui.model.Recipe

data class RecipeAndOwner(
    @Embedded
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = "owner_id",
        entityColumn = "_id"
    )
    val owner: AccountEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id"
    )
    val steps: List<StepEntity>,
    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id"
    )
    val likes: List<LikeEntity>,

    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id",
        entity = RecipeIngredientCrossRef::class
    )
    val ingredients: List<IngredientAndCrossRef>
)

fun RecipeAndOwner.toRecipe(accountId: String?): com.anbui.model.Recipe {
    return com.anbui.model.Recipe(
        id = recipe.id,
        title = recipe.title,
        imageUrl = recipe.imageUrl,
        description = recipe.description,
        isLike = likes.any { like -> like.accountId == accountId },
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
            com.anbui.model.IngredientItem(
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

