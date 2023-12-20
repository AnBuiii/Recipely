package com.anbui.recipely.feature.create_recipe.add_instruction

import android.net.Uri

data class AddInstructionState(
    val instruction: String = "",
    val uri: Uri? = null,
    val id: String? = null,
    val period: String = "",
    val success: Boolean = false,
    val error: String? = null
)
