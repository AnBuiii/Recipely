package com.anbui.recipely.feature.create_recipe.add_ingredient

import com.anbui.recipely.core.model.Ingredient
import com.anbui.recipely.core.model.UnitType

sealed class AddIngredientEvent {
    data class EnterIngredientName(val value: String) : AddIngredientEvent()
    data class EnterAmount(val value: String) : AddIngredientEvent()
    data class ChooseIngredient(val ingredient: Ingredient) : AddIngredientEvent()
    data object AddIngredient : AddIngredientEvent()
}
