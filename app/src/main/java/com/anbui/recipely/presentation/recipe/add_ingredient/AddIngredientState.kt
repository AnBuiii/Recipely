package com.anbui.recipely.presentation.recipe.add_ingredient

import com.anbui.recipely.domain.models.UnitType

data class AddIngredientState(
    val selectedIngredientId: String = "",
    val amount: String  = "",
    val selectedUnit: UnitType = UnitType.Unit,
    val isSearching: Boolean = false
)