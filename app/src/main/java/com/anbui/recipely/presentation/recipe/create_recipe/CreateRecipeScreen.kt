package com.anbui.recipely.presentation.recipe.create_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.recipe.create_recipe.components.IngredientsSection
import com.anbui.recipely.presentation.recipe.create_recipe.components.InstructionSection
import com.anbui.recipely.presentation.recipe.create_recipe.components.OverviewSection
import com.anbui.recipely.presentation.ui.components.StandardProgressIndicator
import com.anbui.recipely.presentation.util.Screen
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun CreateRecipeScreen(
    navController: NavController,
    createRecipeViewModel: CreateRecipeViewModel
) {
    val steps = listOf("Overview", "Ingredients", "Instructions", "Review")
    val pagerState = rememberPagerState(pageCount = { steps.size })
    val coroutineScope = rememberCoroutineScope()
    val ingredientId =
        navController.currentBackStackEntry?.savedStateHandle?.get<String?>("ingredientId")
    val amount = navController.currentBackStackEntry?.savedStateHandle?.get<Double?>("amount")
    val uiState by createRecipeViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(ingredientId, amount) {
        if (ingredientId != null && amount != null) {
            createRecipeViewModel.onEvent(CreateRecipeEvent.AddIngredient(ingredientId, amount))
        }
    }
    Column() {
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
                            navController.navigateUp()
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
            indicatorProgress = (pagerState.currentPage).toFloat() / steps.size
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
                        onEvent = createRecipeViewModel::onEvent,
                        title = uiState.title
                    )

                    1 -> IngredientsSection(
                        onEvent = createRecipeViewModel::onEvent,
                        ingredients = uiState.ingredientItems,
                        onAddIngredientClick = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "ingredientId",
                                null
                            )
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "amount",
                                null
                            )
                            navController.navigate(
                                Screen.AddIngredientScreen.route
                            )
                        }
                    )

                    2 -> InstructionSection(
                        steps = uiState.steps,
                        onAddInstructionClick = {},
                        onEvent = createRecipeViewModel::onEvent
                    )
                }
            }
        }
    }
}
