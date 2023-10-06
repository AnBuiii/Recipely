package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import com.anbui.recipely.data.local.entities.IngredientEntity

data class IngredientWithAmount(
    @Embedded()
    val ingredientEntity: IngredientEntity,
    val amount: Int
)
