package com.anbui.recipely.feature.account.my_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.RecipelyHorizontallyCard
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.LightGreen
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.feature.account.R

@Composable
fun MyRecipeRoute(
    onBack: () -> Unit,
    onNavigateToRecipe: (String) -> Unit,
    myRecipeViewModel: MyRecipeViewModel = hiltViewModel()
) {
    MyRecipeScreen(
        onBack = onBack,
        onNavigateToRecipe = onNavigateToRecipe,
        viewModel = myRecipeViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyRecipeScreen(
    onBack: () -> Unit,
    onNavigateToRecipe: (String) -> Unit,
    viewModel: MyRecipeViewModel
) {
    val recipes by viewModel.currentRecipes.collectAsStateWithLifecycle()
    Column {
        StandardToolbar(
            title = stringResource(R.string.my_recipe),
            onBack = onBack,
            showBackArrow = true
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(recipes, key = { it.id }) { recipe ->
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                            viewModel.deleteRecipe(recipe.id)
                            true
                        } else false
                    }, positionalThreshold = { 150.dp.toPx() })
                SwipeToDismiss(
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    background = {
                        DismissBackground(dismissState)
                    },
                    dismissContent = {
                        RecipelyHorizontallyCard(
                            recipe = recipe,
                            modifier = Modifier
                                .padding(
                                    horizontal = SpaceLarge,
                                    vertical = SpaceSmall + SpaceTiny
                                ),
                            onClick = { onNavigateToRecipe(recipe.id) },
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color.Red
        DismissDirection.EndToStart -> LightGreen
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.StartToEnd) Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart) Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = "Archive"
        )
    }
}