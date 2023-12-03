package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.domain.models.Recipe

data class IngredientInRecipe(
    @Embedded
    val ingredient: IngredientEntity,

    @Relation(
        parentColumn = "_id",
        entityColumn = "ingredient_id",
        entity = RecipeIngredientCrossRef::class
    )
    val recipes: List<RecipeAndCrossRef>,
)

fun IngredientInRecipe.toRecipes(accountId: String?): List<Recipe>{
    return recipes.map {
        it.recipe.toRecipe(accountId)
    }
}

data class RecipeAndCrossRef(
    @Embedded
    val crossRef: RecipeIngredientCrossRef,

    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "_id",
        entity = RecipeEntity::class
    )
    val recipe: RecipeAndOwner
)
