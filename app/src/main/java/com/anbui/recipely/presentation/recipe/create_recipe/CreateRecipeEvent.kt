package com.anbui.recipely.presentation.recipe.create_recipe

import android.net.Uri
import com.anbui.recipely.domain.models.Ingredient

sealed class CreateRecipeEvent {
    data class EnterTitle(val value: String) : CreateRecipeEvent()
    data class EditImage(val value: Uri, val index: Int) : CreateRecipeEvent()
    data class AddImage(val value: Uri) : CreateRecipeEvent()
    data class RemoveImage(val index: Int) : CreateRecipeEvent()
    data class SelectIngredient(val value: Ingredient) : CreateRecipeEvent()
    data class SwapIngredient(val from: Int, val to: Int) : CreateRecipeEvent()
    data class SwapInstruction(val from: Int, val to: Int) : CreateRecipeEvent()
    data class AddIngredient(val ingredientId: String, val amount: Double) : CreateRecipeEvent()
    data class AddInstruction(
        val instructionId: String,
        val instruction: String,
        val period: Double
    ) : CreateRecipeEvent()
}
