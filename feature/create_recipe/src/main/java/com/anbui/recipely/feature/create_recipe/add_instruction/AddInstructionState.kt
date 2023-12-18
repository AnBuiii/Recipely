package com.anbui.recipely.feature.create_recipe.add_instruction

data class AddInstructionState(
    val instruction: String = "",
    val id: String? = null,
    val period: String = "",
    val success: Boolean = false,
    val error: String? = null
)
