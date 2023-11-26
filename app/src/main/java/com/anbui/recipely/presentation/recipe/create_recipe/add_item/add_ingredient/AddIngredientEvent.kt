package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_ingredient

import com.anbui.recipely.domain.models.Ingredient
import com.anbui.recipely.domain.models.UnitType

sealed class AddIngredientEvent {
    data class EnterIngredientName(val value: String) : AddIngredientEvent()
    data class EnterAmount(val value: String) : AddIngredientEvent()
    data class EnterUnit(val value: String) : AddIngredientEvent()
    data class ChooseIngredient(val ingredient: Ingredient) : AddIngredientEvent()
    data class ChooseUnit(val unit: UnitType) : AddIngredientEvent()
    data object AddIngredient: AddIngredientEvent()
}
