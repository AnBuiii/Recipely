package com.anbui.recipely.presentation.create_recipe.components

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Ingredient
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.presentation.components.StandardCard
import com.anbui.recipely.presentation.components.StandardTextField
import com.anbui.recipely.presentation.create_recipe.CreateRecipeEvent
import com.anbui.recipely.presentation.ui.theme.SpaceLarge

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun IngredientsSection(
    searchText: String,
    onEvent: (CreateRecipeEvent) -> Unit,
    ingredients: List<IngredientItem>,
    searchResult: List<Ingredient>,
    onAddIngredientClick: () -> Unit
) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val focusRequester = remember { FocusRequester() }
    Column {
        DragDropList(
            items = ingredients,
            onMove = { from, to -> onEvent(CreateRecipeEvent.SwapIngredient(from, to)) },
            onAddIngredientClick = onAddIngredientClick
        )
        Text(text = "asd")
    }

    

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .clickable(
//                indication = null,
//                interactionSource = remember { MutableInteractionSource() },
//                onClick = focusManager::clearFocus
//            )
//    ) {
//        StandardTextField(
//            text = searchText, onValueChange = { onEvent(CreateRecipeEvent.EnterSearch(it)) },
//            leadingIcon = painterResource(
//                id = R.drawable.ic_search
//            ),
//            hint = stringResource(id = R.string.search),
//            modifier = Modifier
//                .padding(horizontal = SpaceLarge)
//                .onFocusChanged {
//                    isTextFieldFocused = it.isFocused
//                },
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    focusManager.clearFocus()
//                }
//            )
//        )
//
//        Box {
//
//
//
//
//            androidx.compose.animation.AnimatedVisibility(
//                visible = isTextFieldFocused,
//                enter = fadeIn(),
//                exit = fadeOut()
//            ) {
//                StandardCard(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = SpaceLarge)
//                        .height(140.dp)
//                ) {
//                    LazyColumn(
//                        modifier = Modifier
//                    ) {
//                        items(searchResult) { item ->
//                            Text(
//                                text = item.name,
//                                modifier = Modifier.clickable {
//                                    onEvent(
//                                        CreateRecipeEvent.SelectIngredient(item)
//                                    )
//                                }
//                            )
//                        }
//                    }
//                }
//
//            }
//
//        }


//    }
}