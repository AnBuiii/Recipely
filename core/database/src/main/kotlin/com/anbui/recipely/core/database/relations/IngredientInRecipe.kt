package com.anbui.recipely.core.database.relations

import android.util.Log
import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.core.database.entities.IngredientEntity
import com.anbui.recipely.core.database.entities.RecipeEntity
import com.anbui.recipely.core.database.entities.RecipeIngredientCrossRef
import com.anbui.recipely.core.model.Recipe

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

fun IngredientInRecipe.toRecipes(accountId: String?): List<Recipe> {
    return recipes.map {
        Log.d("An ne", it.toString())
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
