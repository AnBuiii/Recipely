package com.anbui.recipely.presentation.recipe.create_recipe.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.anbui.recipely.domain.models.Step
import com.anbui.recipely.presentation.recipe.create_recipe.CreateRecipeEvent

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun InstructionSection(
    steps: List<Step>,
    onEvent: (CreateRecipeEvent) -> Unit,
    onAddInstructionClick: () -> Unit
) {
    Column {
        InstructionDragDropList(
            items = steps,
            onMove = { from, to -> onEvent(CreateRecipeEvent.SwapInstruction(from, to)) },
            onAddInstructionClick = onAddInstructionClick
        )
    }
}
