package com.anbui.recipely.feature.recipe_detail.cooking_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.designsystem.theme.ThinGrey
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.feature.recipe_detail.ViewMode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBottomSheet(
    recipe: Recipe,
    serving: Int,
    isOpen: Boolean,
    viewMode: ViewMode,
    onChangeOpenState: (Boolean) -> Unit,
    onChangeViewMode: (ViewMode) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val ingredientMode by remember(viewMode) {
        derivedStateOf {
            viewMode is ViewMode.Ingredients
        }
    }

    if (isOpen) {
        ModalBottomSheet(
            onDismissRequest = { onChangeOpenState(false) },
            sheetState = bottomSheetState,
            windowInsets = WindowInsets(0),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SpaceLarge)
                    .height(54.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ThinGrey
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(SpaceTiny)
                        .clip(MaterialTheme.shapes.large),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = if (ingredientMode) MaterialTheme.colorScheme.primary else ThinGrey
                        ),
                        shape = MaterialTheme.shapes.large,
                        onClick = {
                            onChangeViewMode(ViewMode.Ingredients)
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Ingredients",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (ingredientMode) TrueWhite else MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = if (!ingredientMode) MaterialTheme.colorScheme.primary else ThinGrey
                        ),
                        shape = MaterialTheme.shapes.large,
                        onClick = {
                            onChangeViewMode(ViewMode.Instructions)
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Instructions",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (!ingredientMode) TrueWhite
                                    else MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(SpaceMedium))

            LazyColumn(
                modifier = Modifier.padding(horizontal = SpaceLarge)
            ) {
                if (viewMode is ViewMode.Ingredients) {
                    ingredientsSection(recipe = recipe, servings = serving)
                } else {
                    instructionsSection(recipe = recipe)
                }
            }
        }
    }
}
