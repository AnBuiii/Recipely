package com.anbui.recipely.feature.create_recipe

import android.net.Uri
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Step

sealed class CreateRecipeEvent {
    data class EnterTitle(val value: String) : CreateRecipeEvent()
    data class EditImage(val value: Uri, val index: Int) : CreateRecipeEvent()
    data class AddImage(val value: Uri) : CreateRecipeEvent()
    data class RemoveImage(val index: Int) : CreateRecipeEvent()
    data class EditDescription(val value: String) : CreateRecipeEvent()
    data class EditServings(val value: String) : CreateRecipeEvent()
    data class SwapIngredient(val from: Int, val to: Int) : CreateRecipeEvent()
    data class SwapInstruction(val from: Int, val to: Int) : CreateRecipeEvent()
    data class AddIngredient(val ingredientItem: IngredientItem) : CreateRecipeEvent()
    data class AddInstruction(val step: Step) : CreateRecipeEvent()
    data object CreateRecipe : CreateRecipeEvent()
}
