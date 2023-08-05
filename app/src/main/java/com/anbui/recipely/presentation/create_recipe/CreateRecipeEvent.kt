package com.anbui.recipely.presentation.create_recipe

import android.net.Uri
import com.anbui.recipely.domain.models.Ingredient

sealed class CreateRecipeEvent {
    data class EnterTitle(val value: String) : CreateRecipeEvent()
    data class EditImage(val value: Uri, val index: Int) : CreateRecipeEvent()
    data class AddImage(val value: Uri) : CreateRecipeEvent()
    data class RemoveImage(val index: Int) : CreateRecipeEvent()
    data class EnterSearch(val value: String) : CreateRecipeEvent()
    data class SelectIngredient(val value: Ingredient) : CreateRecipeEvent()
    data class SwapIngredient(val from: Int, val to: Int) : CreateRecipeEvent()
    data object Swap : CreateRecipeEvent()
}
