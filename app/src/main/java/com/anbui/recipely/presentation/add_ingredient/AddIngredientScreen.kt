package com.anbui.recipely.presentation.add_ingredient

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardCard
import com.anbui.recipely.presentation.components.StandardTextField
import com.anbui.recipely.presentation.components.StandardToolbar
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
                text = addIngredientViewModel.ingredientName.value,
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
                ),
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
                                text = addIngredientViewModel.amount.value,
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
                                text = addIngredientViewModel.unit.value,
                                onValueChange = {
                                    addIngredientViewModel.onEvent(
                                        AddIngredientEvent.EnterUnit(it)
                                    )
                                },
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

                    androidx.compose.animation.AnimatedVisibility(
                        visible = isUnitFocused,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        StandardCard(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(end = SpaceLarge)
//                            .height(140.dp)
                        ) {
                            Column(
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                addIngredientViewModel.searchResult.take(4).forEach { item ->
                                    Text(
                                        text = item.name,
                                        modifier = Modifier
                                            .clickable {
                                                addIngredientViewModel.onEvent(
                                                    AddIngredientEvent.EnterUnit(item.name)
                                                )
                                            }
                                    )
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {

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
                            addIngredientViewModel.searchResult.take(4).forEach { item ->
                                Text(
                                    text = item.name,
                                    modifier = Modifier
                                        .clickable {
                                            addIngredientViewModel.onEvent(
                                                AddIngredientEvent.EnterUnit(item.name)
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