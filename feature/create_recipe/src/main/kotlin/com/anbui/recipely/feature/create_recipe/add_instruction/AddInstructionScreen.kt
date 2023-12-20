package com.anbui.recipely.feature.create_recipe.add_instruction

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardTextField
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.ThinGrey
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.create_recipe.CreateRecipeViewModel
import com.anbui.recipely.feature.create_recipe.R

@Composable
fun AddInstructionRoute(
    onBack: () -> Unit,
    createRecipeViewModel: CreateRecipeViewModel
) {
    AddInstructionScreen(onBack = onBack, viewModel = createRecipeViewModel)
}

@Composable
fun AddInstructionScreen(
    onBack: () -> Unit,
    viewModel: CreateRecipeViewModel,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val uiState by viewModel.addInstructionState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.error) {
        if (!uiState.error.isNullOrEmpty()) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onBack()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onAddInstructionEvent(
                AddInstructionEvent.Dispose
            )
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                viewModel.onAddInstructionEvent(
                    AddInstructionEvent.EnterCoverImage(it)
                )
            }
        }
    )

    Column {
        StandardToolbar(
            title = stringResource(R.string.add_instruction),
            showBackArrow = true,
            onBack = onBack
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
            Spacer(modifier = Modifier.height(SpaceMedium))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SpaceLarge)
                    .aspectRatio(1f),
                colors = CardDefaults.cardColors(containerColor = ThinGrey)
            ) {
                if (uiState.uri == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(SpaceMedium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_recipely),
                                contentDescription = stringResource(
                                    R.string.recipely
                                ),
                                modifier = Modifier.size(36.dp)
                            )
                            Text(text = stringResource(R.string.add_instruction_image))
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AsyncImage(
                            model = uiState.uri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        FilledIconButton(
                            onClick = {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(SpaceMedium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = stringResource(
                                    R.string.edit
                                )
                            )
                        }

                        FilledIconButton(
                            onClick = {
                                viewModel.onAddInstructionEvent(
                                    AddInstructionEvent.EnterCoverImage(null)
                                )
                            },
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(SpaceMedium)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = stringResource(
                                    R.string.edit
                                )
                            )
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.instruction),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = SpaceLarge)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.instruction,
                onValueChange = {
                    viewModel.onAddInstructionEvent(
                        AddInstructionEvent.EnterInstruction(it)
                    )
                },
                hint = stringResource(R.string.instruction_hint),
                modifier = Modifier.padding(horizontal = SpaceLarge),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.period),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = SpaceLarge)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.period,
                onValueChange = {
                    viewModel.onAddInstructionEvent(
                        AddInstructionEvent.EnterPeriod(it)
                    )
                },
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
                onClick = {
                    viewModel.onAddInstructionEvent(
                        AddInstructionEvent.AddInstruction
                    )
                },
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