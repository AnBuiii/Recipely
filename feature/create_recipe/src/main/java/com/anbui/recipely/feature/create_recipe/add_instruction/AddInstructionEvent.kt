package com.anbui.recipely.feature.create_recipe.add_instruction

import android.net.Uri
import com.anbui.recipely.core.model.Step

sealed class AddInstructionEvent {
    data class Init(val step: Step? = null) : AddInstructionEvent()
    data class EnterInstruction(val value: String) : AddInstructionEvent()
    data class EnterPeriod(val value: String) : AddInstructionEvent()
    data class EnterCoverImage(val uri: Uri?) : AddInstructionEvent()
    data object AddInstruction : AddInstructionEvent()
    data object Dispose : AddInstructionEvent()
}
