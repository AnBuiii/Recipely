package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.RecipeEntity

data class RecipeWithIngredient(
    @Embedded val recipeEntity: RecipeEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "_id",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    )
    val ingredient: List<IngredientEntity>
)
