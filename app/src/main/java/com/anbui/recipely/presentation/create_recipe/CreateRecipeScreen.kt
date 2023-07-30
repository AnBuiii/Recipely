package com.anbui.recipely.presentation.create_recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardProgressIndicator
import com.anbui.recipely.presentation.create_recipe.components.OverviewSection
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun CreateRecipeScreen(
    navController: NavController,
    createRecipeViewModel: CreateRecipeViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()

    val steps = listOf("Overview", "Ingredients", "Instructions", "Review")
    val coroutineScope = rememberCoroutineScope()
    Column(
    ) {
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
                        }else {
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
                        if (pagerState.currentPage + 1 != steps.size)
                            stringResource(id = R.string.next)
                        else stringResource(
                            R.string.finish
                        ),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary)
                    )
                }
            }
        )
        StandardProgressIndicator(indicatorProgress = (pagerState.currentPage + 1).toFloat() / steps.size)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {




            HorizontalPager(
                pageCount = 5,
                state = pagerState,
                userScrollEnabled = false
            ) {
                when (it) {
                    0 -> OverviewSection(
                        selectedImages = createRecipeViewModel.images,
                        onEvent = createRecipeViewModel::onEvent,
                        title = createRecipeViewModel.title.value
                    )
                }
            }
        }

    }

}

