package com.anbui.recipely.presentation.recipe.create_recipe

import android.net.Uri
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.Step

data class CreateRecipeState(
    val coverImages: List<Uri> = emptyList(),
    val title: String = "",
    val description: String = "",
    val serving: String = "",
    val ingredientItems: List<IngredientItem> = emptyList(),
    val steps: List<Step> = emptyList(),
    val success: Boolean = false,
    val error: String = ""
)