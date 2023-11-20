package com.anbui.recipely.presentation.recipe.add_ingredient

import com.anbui.recipely.domain.models.UnitType

data class AddIngredientState(
    val selectedIngredientId: String = "",
    val amount: String  = "",
    val isSearching: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)