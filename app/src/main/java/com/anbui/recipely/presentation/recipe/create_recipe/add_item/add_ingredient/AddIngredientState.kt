package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_ingredient

import com.anbui.recipely.domain.models.UnitType

data class AddIngredientState(
    val selectedIngredientId: String = "",
    val amount: String  = "",
    val isSearching: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val isEdit: Boolean = false
)