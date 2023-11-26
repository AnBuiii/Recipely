package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_instruction

data class AddInstructionState(
    val instruction: String = "",
    val id: String? = null,
    val period: String = "",
    val success: Boolean = false,
    val error: String? = null
)
