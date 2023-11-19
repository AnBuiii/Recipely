package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.UnitType.Companion.toUnitType

data class OrderAndIngredientCrossRef(
    @Embedded
    val crossRef: OrderIngredientCrossRef,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "_id"
    )
    val ingredient: IngredientEntity
){
    fun toIngredientItem(): IngredientItem{
        return IngredientItem(
            ingredientId = this.ingredient.id,
            name = this.ingredient.name,
            amount = this.crossRef.amount,
            unit = this.ingredient.unit,
            imageUrl = this.ingredient.imageUrl,
            price = this.ingredient.price
        )
    }
}
