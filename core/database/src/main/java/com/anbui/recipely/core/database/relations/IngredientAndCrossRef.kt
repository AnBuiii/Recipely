package com.anbui.recipely.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.core.database.entities.IngredientEntity

data class IngredientAndCrossRef(
    @Embedded
    val crossRef: RecipeIngredientCrossRef,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "_id"
    )
    val ingredient: IngredientEntity
)
