package com.anbui.recipely.feature.recipe_detail.cooking_detail

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.util.UnstableApi
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardProgressIndicator
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceHuge
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.core.model.NotMediaType
import com.anbui.recipely.feature.recipe_detail.R
import com.anbui.recipely.feature.recipe_detail.cooking_detail.components.DetailBottomSheet
import com.anbui.recipely.feature.recipe_detail.cooking_detail.components.StandardVideoPlayer
import com.anbui.recipely.feature.recipe_detail.cooking_detail.components.Timer
import com.anbui.recipely.feature.recipe_detail.RecipeDetailViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun CookingRoute(
    onBack: () -> Unit,
    onBackToRecipe: () -> Unit,
    recipeDetailViewModel: RecipeDetailViewModel
) {
    CookingDetailScreen(
        onBack = onBack,
        onBackToRecipe = onBackToRecipe,
        viewModel = recipeDetailViewModel
    )
}


@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
fun CookingDetailScreen(
    onBackToRecipe: () -> Unit,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val recipe by viewModel.recipe.collectAsStateWithLifecycle()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    DetailBottomSheet(
        recipe = recipe,
        serving = 4,
        isOpen = openBottomSheet,
        viewMode = uiState.viewMode,
        onChangeOpenState = { openBottomSheet = it },
        onChangeViewMode = viewModel::changeViewMode
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StandardToolbar(
            title = recipe.title,
            showBackArrow = true,
            navActions = {
                IconButton(
                    onClick = {
                        openBottomSheet = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_list),
                        contentDescription = stringResource(R.string.list)
                    )
                }
            },
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        val mediaPagerState = rememberPagerState { recipe.instructions.size }

        HorizontalPager(
            state = mediaPagerState,
            contentPadding = PaddingValues(horizontal = SpaceHuge),
            pageSpacing = SpaceMedium

        ) { page ->
            val pageOffset = (
                    (mediaPagerState.currentPage - page) + mediaPagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

            Card(
                Modifier
                    .graphicsLayer {
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        val scale = lerp(
                            start = 0.9f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = scale
                    }
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                    }
            ) {
                if (page < recipe.instructions.size) {
                    if (recipe.instructions[page].type == NotMediaType.Image) {
                        AsyncImage(
                            model = recipe.instructions[page].mediaUrl,
                            contentDescription = recipe.instructions[page].instruction,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    } else {
                        StandardVideoPlayer(
                            uri = Uri.parse(recipe.instructions[page].mediaUrl),
                            stop = page != mediaPagerState.currentPage
                        )
                    }
                }
            }
        }
        val isFinish by remember(mediaPagerState.currentPage) {
            derivedStateOf {
                (mediaPagerState.currentPage + 1) >= recipe.instructions.size
            }
        }

        if (recipe.instructions.isNotEmpty()) {
            LaunchedEffect(mediaPagerState.currentPage) {
                viewModel.setTimer(recipe.instructions[mediaPagerState.currentPage].period)
            }
            Spacer(modifier = Modifier.height(SpaceLarge))

            AnimatedContent(
                targetState = mediaPagerState.currentPage,
                label = "instruction",
                transitionSpec = { ->
                    slideInHorizontally { if (it > 0) it else -it } togetherWith slideOutHorizontally { -it }
                }
            ) {
                Text(
                    recipe.instructions[it].instruction,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = SpaceLarge),
                    maxLines = 6,
                    minLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Timer(
                totalTime = state.timeDuration,
                currentTime = state.remainingTime,
                buttonState = state.timerStatus,
                onButtonClick = {
                    viewModel.buttonSelection()
                }
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.step),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )
            Text(
                text = "${mediaPagerState.currentPage + 1} of ${recipe.instructions.size}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )
        }

        StandardProgressIndicator(
            indicatorProgress = (mediaPagerState.currentPage + 1).toFloat() / recipe.instructions.size
        )

        Button(
            onClick = {
                if (!isFinish) {
                    coroutineScope.launch {
                        mediaPagerState.animateScrollToPage(mediaPagerState.currentPage + 1)
                    }
                } else {
                    onBackToRecipe()
                }
            },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = SpaceLarge,
                    start = SpaceLarge,
                    end = SpaceLarge,
                    top = SpaceSmall
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )

        ) {
            Text(
                text = if (!isFinish) {
                    stringResource(
                        R.string.next_step
                    )
                } else {
                    stringResource(
                        R.string.completed
                    )
                },
                style = MaterialTheme.typography.bodyMedium.copy(TrueWhite),
                modifier = Modifier.padding(vertical = SpaceSmall)
            )
        }
    }
}
