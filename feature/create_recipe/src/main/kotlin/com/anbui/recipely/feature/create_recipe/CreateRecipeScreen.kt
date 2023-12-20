package com.anbui.recipely.feature.create_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.StandardProgressIndicator
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.feature.create_recipe.add_ingredient.AddIngredientEvent
import com.anbui.recipely.feature.create_recipe.add_instruction.AddInstructionEvent
import com.anbui.recipely.feature.create_recipe.components.IngredientsSection
import com.anbui.recipely.feature.create_recipe.components.InstructionSection
import com.anbui.recipely.feature.create_recipe.components.OverviewSection
import kotlinx.coroutines.launch

@Composable
fun CreateRecipeRoute(
    onBack: () -> Unit,
    onNavigateToIngredient: () -> Unit,
    onNavigateToInstruction: () -> Unit,
    createRecipeViewModel: CreateRecipeViewModel
) {
    CreateRecipeScreen(
        onBack = onBack,
        onNavigateToIngredient = onNavigateToIngredient,
        onNavigateToInstruction = onNavigateToInstruction,
        viewModel = createRecipeViewModel
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreen(
    onBack: () -> Unit,
    onNavigateToIngredient: () -> Unit,
    onNavigateToInstruction: () -> Unit,
    viewModel: CreateRecipeViewModel
) {
    val steps = listOf("Overview", "Ingredients", "Instructions", "Review")
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onBack()
        }
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = steps[pagerState.currentPage],
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (pagerState.currentPage != 0) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        } else {
                            onBack()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            actions = {
                TextButton(
                    onClick = {
                        if (pagerState.currentPage + 1 != steps.size) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            viewModel.onEvent(CreateRecipeEvent.CreateRecipe)
                        }
                    }
                ) {
                    Text(
                        text =
                        if (pagerState.currentPage + 1 != steps.size) {
                            stringResource(id = R.string.next)
                        } else {
                            stringResource(
                                R.string.finish
                            )
                        },
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }
        )
        StandardProgressIndicator(
            indicatorProgress = (pagerState.currentPage).toFloat() / (steps.size - 1)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> OverviewSection(
                        selectedImages = uiState.coverImages,
                        onEvent = viewModel::onEvent,
                        title = uiState.title,
                        description = uiState.description,
                        servings = uiState.serving
                    )

                    1 -> IngredientsSection(
                        onEvent = viewModel::onEvent,
                        ingredients = uiState.ingredientItems,
                        onAddIngredientClick = onNavigateToIngredient,
                        onEditIngredient = {
                            viewModel.onAddIngredientEvent(AddIngredientEvent.Init(it))
                            onNavigateToIngredient()
                        }
                    )

                    2 -> InstructionSection(
                        steps = uiState.steps,
                        onAddInstructionClick = onNavigateToInstruction,
                        onEvent = viewModel::onEvent,
                        onEditClick = {
                            viewModel.onAddInstructionEvent(AddInstructionEvent.Init(it))
                            onNavigateToInstruction()
                        }
                    )

                    3 -> {
                        Spacer(modifier = Modifier.padding(top = SpaceLarge))
                        Text(text = stringResource(R.string.this_feature_still_construction))
                    }
                }
            }
        }
    }
}
