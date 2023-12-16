package com.anbui.recipely.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.core.database.entities.IngredientEntity

data class AccountWithIngredient(
    @Embedded
    val cart: IngredientAccountCrossRef,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "_id"
    )
    val ingredient: IngredientEntity
) {
    fun toIngredientItem(): com.anbui.model.IngredientItem {
        return com.anbui.model.IngredientItem(
            ingredientId = this.ingredient.id,
            name = this.ingredient.name,
            amount = this.cart.amount,
            unit = this.ingredient.unit,
            imageUrl = this.ingredient.imageUrl,
            price = this.ingredient.price
        )
    }
}