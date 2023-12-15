package com.anbui.recipely.presentation.recipe.create_recipe

import android.net.Uri
import com.anbui.model.IngredientItem
import com.anbui.model.Step

data class CreateRecipeState(
    val coverImages: List<Uri> = emptyList(),
    val title: String = "",
    val description: String = "",
    val serving: String = "",
    val ingredientItems: List<com.anbui.model.IngredientItem> = emptyList(),
    val steps: List<com.anbui.model.Step> = emptyList(),
    val success: Boolean = false,
    val error: String = ""
)