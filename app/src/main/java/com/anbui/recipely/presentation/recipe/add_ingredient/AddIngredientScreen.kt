package com.anbui.recipely.presentation.recipe.add_ingredient

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.components.StandardCard
import com.anbui.recipely.presentation.ui.components.StandardTextField
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@ExperimentalMaterial3Api
@Composable
fun AddIngredientScreen(
    navController: NavController,
    addIngredientViewModel: AddIngredientViewModel = hiltViewModel()
) {
    var isNameFocused by remember { mutableStateOf(false) }
    var isUnitFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val ingredientName by addIngredientViewModel.ingredientName.collectAsStateWithLifecycle()
    val uiState by addIngredientViewModel.state.collectAsStateWithLifecycle()
    val ingredients by addIngredientViewModel.ingredients.collectAsStateWithLifecycle()
    val unit by addIngredientViewModel.unit.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.error) {
        if (!uiState.error.isNullOrEmpty()) {
            Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            Log.d("Success", "Hello")
            navController.previousBackStackEntry?.savedStateHandle?.set(
                "ingredientId",
                uiState.selectedIngredientId
            )
            navController.previousBackStackEntry?.savedStateHandle?.set(
                "amount",
                uiState.amount.toDouble()
            )
            navController.popBackStack()
        }
    }

    Column {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.add_ingredient),
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
                text = stringResource(R.string.ingredient_name),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = SpaceLarge)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = ingredientName,
                onValueChange = {
                    addIngredientViewModel.onEvent(
                        AddIngredientEvent.EnterIngredientName(
                            it
                        )
                    )
                },
                hint = stringResource(R.string.ingredient_name_hint),
                modifier = Modifier
                    .onFocusChanged {
                        isNameFocused = it.isFocused
                    }
                    .padding(horizontal = SpaceLarge),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Box {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpaceLarge),
                        horizontalArrangement = Arrangement.spacedBy(SpaceSmall)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(R.string.amount),
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(SpaceMedium))
                            StandardTextField(
                                text = uiState.amount,
                                onValueChange = {
                                    addIngredientViewModel.onEvent(
                                        AddIngredientEvent.EnterAmount(it)
                                    )
                                },
                                hint = stringResource(R.string.enter_amount_hint),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                maxLines = 1
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(R.string.unit),
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(SpaceMedium))
                            StandardTextField(
                                text = unit,
                                isEnabled = false,
                                onValueChange = {},
                                hint = stringResource(R.string.enter_unit_hint),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                modifier = Modifier.onFocusChanged {
                                    isUnitFocused = it.isFocused
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            addIngredientViewModel.onEvent(
                                AddIngredientEvent.AddIngredient
                            )
                        },
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SpaceLarge)

                    ) {
                        Text(
                            text = stringResource(id = R.string.add_ingredient),
                            style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                            modifier = Modifier.padding(vertical = SpaceSmall)
                        )
                    }
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = isNameFocused,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    StandardCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpaceLarge)
//                            .height(140.dp)
                    ) {
                        Column(
                            modifier = Modifier.wrapContentHeight()
                        ) {
                            ingredients.forEach { item ->
                                Text(
                                    text = item.name,
                                    modifier = Modifier
                                        .clickable {
                                            addIngredientViewModel.onEvent(
                                                AddIngredientEvent.ChooseIngredient(item)
                                            )
                                        }
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
