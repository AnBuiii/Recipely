package com.anbui.recipely.presentation.create_recipe

import android.net.Uri

sealed class CreateRecipeEvent {
    data class EnterTitle(val value: String) : CreateRecipeEvent()
    data class EditImage(val value: Uri, val index: Int) : CreateRecipeEvent()
    data class AddImage(val value: Uri) : CreateRecipeEvent()
    data class RemoveImage(val index: Int): CreateRecipeEvent()
    data object Swap : CreateRecipeEvent()
}
