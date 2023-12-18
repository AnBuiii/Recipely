package com.anbui.recipely.feature.create_recipe.add_ingredient

data class AddIngredientState(
    val selectedIngredientId: String = "",
    val amount: String = "",
    val unit: String = "",
    val isSearching: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val isEdit: Boolean = false
)