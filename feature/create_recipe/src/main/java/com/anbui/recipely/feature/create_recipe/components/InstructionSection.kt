package com.anbui.recipely.feature.create_recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.anbui.recipely.core.model.Step
import com.anbui.recipely.feature.create_recipe.CreateRecipeEvent

@Composable
fun InstructionSection(
    steps: List<Step>,
    onEvent: (CreateRecipeEvent) -> Unit,
    onAddInstructionClick: () -> Unit,
    onEditClick: (Step) -> Unit
) {
    Column {
        InstructionDragDropList(
            items = steps,
            onMove = { from, to -> onEvent(CreateRecipeEvent.SwapInstruction(from, to)) },
            onAddInstructionClick = onAddInstructionClick,
            onEditClick = onEditClick
        )
    }
}
