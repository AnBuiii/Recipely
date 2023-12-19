package com.anbui.recipely.feature.create_recipe.add_ingredient

import com.anbui.recipely.core.model.Ingredient

data class AddIngredientState(
    val name: String = "",
    val selectedIngredientId: String = "",
    val ingredients: List<Ingredient> = emptyList(),
    val amount: String = "",
    val unit: String = "",
    val isSearching: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val isEdit: Boolean = false
)