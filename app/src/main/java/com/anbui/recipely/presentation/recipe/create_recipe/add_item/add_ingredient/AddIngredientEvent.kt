package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_ingredient

import com.anbui.model.Ingredient
import com.anbui.model.UnitType

sealed class AddIngredientEvent {
    data class EnterIngredientName(val value: String) : AddIngredientEvent()
    data class EnterAmount(val value: String) : AddIngredientEvent()
    data class EnterUnit(val value: String) : AddIngredientEvent()
    data class ChooseIngredient(val ingredient: com.anbui.model.Ingredient) : AddIngredientEvent()
    data class ChooseUnit(val unit: com.anbui.model.UnitType) : AddIngredientEvent()
    data object AddIngredient : AddIngredientEvent()
}
