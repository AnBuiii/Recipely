package com.anbui.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.database.entities.IngredientEntity

data class IngredientAndCrossRef(
    @Embedded
    val crossRef: RecipeIngredientCrossRef,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "_id"
    )
    val ingredient: IngredientEntity
)
