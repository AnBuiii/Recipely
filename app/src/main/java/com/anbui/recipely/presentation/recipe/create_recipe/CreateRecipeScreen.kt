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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.UnitType
import com.anbui.recipely.presentation.ui.components.StandardProgressIndicator
import com.anbui.recipely.presentation.recipe.create_recipe.components.IngredientsSection
import com.anbui.recipely.presentation.recipe.create_recipe.components.InstructionSection
import com.anbui.recipely.presentation.recipe.create_recipe.components.OverviewSection
import com.anbui.recipely.presentation.util.Screen
import kotlin.random.Random
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun CreateRecipeScreen(
    navController: NavController,
    backStackEntry: NavBackStackEntry,
    createRecipeViewModel: CreateRecipeViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    val steps = listOf("Overview", "Ingredients", "Instructions", "Review")
    val coroutineScope = rememberCoroutineScope()
    val text = backStackEntry.savedStateHandle.get<String>("ingredient_name")

    LaunchedEffect(text) {
        if (text != null) {
            createRecipeViewModel.onEvent(
                CreateRecipeEvent.AddIngredient(
                    IngredientItem(
                        ingredientId = "${Random.nextInt(0, 1000)}",
                        name = text,
                        amount = 1.4f,
                        unit = UnitType.Unit,
                        imageUrl = null
                    )
                )
            )
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
            indicatorProgress = (pagerState.currentPage + 1).toFloat() / steps.size
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
                        selectedImages = createRecipeViewModel.images,
                        onEvent = createRecipeViewModel::onEvent,
                        title = createRecipeViewModel.title.value
                    )

                    1 -> IngredientsSection(
                        searchText = createRecipeViewModel.searchText.value,
                        onEvent = createRecipeViewModel::onEvent,
                        ingredients = createRecipeViewModel.ingredients,
                        searchResult = createRecipeViewModel.searchResult,
                        onAddIngredientClick = {
                            navController.navigate(
                                Screen.AddIngredientScreen.route
                            )
                        }
                    )
                    2 -> InstructionSection(
                        steps = createRecipeViewModel.steps,
                        onAddInstructionClick = {},
                        onEvent = createRecipeViewModel::onEvent
                    )
                }
            }
        }
    }
}
