package com.anbui.database.entities.relations

import android.util.Log
import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.database.entities.IngredientEntity
import com.anbui.database.entities.RecipeEntity
import com.anbui.model.Recipe

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

fun IngredientInRecipe.toRecipes(accountId: String?): List<com.anbui.model.Recipe>{
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
