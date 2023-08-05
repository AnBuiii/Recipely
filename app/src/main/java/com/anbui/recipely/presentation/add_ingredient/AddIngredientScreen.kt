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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardCard
import com.anbui.recipely.presentation.components.StandardTextField
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.create_recipe.CreateRecipeEvent
import com.anbui.recipely.presentation.create_recipe.CreateRecipeViewModel
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall

@ExperimentalMaterial3Api
@Composable
fun AddIngredientScreen(
    navController: NavController,
    createRecipeViewModel: CreateRecipeViewModel = hiltViewModel()
) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5","Option 1", "Option 2", "Option 3", "Option 4", "Option 5","Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
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
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth().padding(horizontal = SpaceLarge)
            ){
                StandardTextField(
                    text = createRecipeViewModel.searchText.value, onValueChange = { createRecipeViewModel.onEvent(CreateRecipeEvent.EnterSearch(it)) },
                    hint = stringResource(R.string.ingredient_name_hint),
                    modifier = Modifier
                        .menuAnchor()
//                        .onFocusChanged {
//                            isTextFieldFocused = it.isFocused
//                        }
                    ,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

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
                                text = createRecipeViewModel.searchText.value, onValueChange = { createRecipeViewModel.onEvent(CreateRecipeEvent.EnterSearch(it)) },
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
//                            ExposedDropdownMenuBox(
//                                expanded = expanded,
//                                onExpandedChange = { expanded = !expanded },
//                            ){
//                                StandardTextField(
//                                    text = createRecipeViewModel.searchText.value, onValueChange = { createRecipeViewModel.onEvent(CreateRecipeEvent.EnterSearch(it)) },
//                                    hint = stringResource(R.string.enter_unit_hint),
//                                    keyboardActions = KeyboardActions(
//                                        onDone = {
//                                            focusManager.clearFocus()
//                                        }
//                                    ),
//                                    trailingIcon = {
////                                        IconButton(onClick = { expanded = !expanded }) {
////                                            Icon(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "show unit hint" )
////                                        }
////                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//                                    },
//                                    modifier = Modifier.menuAnchor(),
////                                    isEnabled = false
//                                )
//                                ExposedDropdownMenu(
//                                    expanded = expanded,
//                                    onDismissRequest = { expanded = false },
//                                ) {
//                                    options.forEach { selectionOption ->
//                                        DropdownMenuItem(
//                                            text = { Text(selectionOption) },
//                                            onClick = {
//                                                selectedOptionText = selectionOption
//                                                expanded = false
//                                            },
//                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
//                                        )
//                                    }
//                                }
//                            }


                        }
                    }


                }



                androidx.compose.animation.AnimatedVisibility(
                    visible = isTextFieldFocused,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    StandardCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpaceLarge)
                            .height(140.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                        ) {
                            items(createRecipeViewModel.searchResult) { item ->
                                Text(
                                    text = item.name,
                                    modifier = Modifier.clickable {
                                        createRecipeViewModel.onEvent(
                                            CreateRecipeEvent.SelectIngredient(item)
                                        )
                                    }
                                )
                            }
                        }
                    }

                }

            }

        }
    }
}