package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_instruction

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.components.StandardTextField
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@ExperimentalMaterial3Api
@Composable
fun AddInstructionScreen(
    navController: NavController,
    addInstructionViewModel: AddInstructionViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val uiState by addInstructionViewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(uiState.error) {
        if (!uiState.error.isNullOrEmpty()) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            navController.previousBackStackEntry?.savedStateHandle?.set(
                "instructionId",
                uiState.id
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
                "instruction",
                uiState.instruction
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
                "period",
                uiState.period.toDouble()
            )
            navController.popBackStack()
        }
    }
    Column {
        StandardToolbar(
            navController = navController,
            title = stringResource(R.string.add_instruction),
            showBackArrow = true
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = focusManager::clearFocus
                )
        ) {
            Text(
                text = stringResource(R.string.instruction),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = SpaceLarge)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.instruction,
                onValueChange = addInstructionViewModel::onChangeInstruction,
                hint = stringResource(R.string.instruction_hint),
                modifier = Modifier.padding(horizontal = SpaceLarge),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
            )

            Text(
                text = stringResource(R.string.period),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = SpaceLarge)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.period,
                onValueChange = addInstructionViewModel::onChangePeriod,
                hint = stringResource(R.string.period_hint),
                modifier = Modifier.padding(horizontal = SpaceLarge),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    },
                ),
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = addInstructionViewModel::addInstruction,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceLarge)

            ) {
                Text(
                    text = if (uiState.id == null) stringResource(id = R.string.add_ingredient)
                    else stringResource(R.string.edit_ingredient),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }
        }
    }
}