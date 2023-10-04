package com.anbui.recipely.presentation.recipe.cooking_detail

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.MediaType
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.components.StandardVideoPlayer
import com.anbui.recipely.presentation.recipe.cooking_detail.components.DetailBottomSheet
import com.anbui.recipely.presentation.components.StandardProgressIndicator
import com.anbui.recipely.presentation.recipe.cooking_detail.components.Timer
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.Screen
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@ExperimentalAnimationApi
@UnstableApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CookingDetailScreen(
    navController: NavController,
    cookingDetailViewModel: CookingDetailViewModel = hiltViewModel()
) {
    val state = cookingDetailViewModel.viewState.value
    val recipe = exampleRecipes[0]
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val corountineScope = rememberCoroutineScope()

    DetailBottomSheet(
        recipe = recipe,
        serving = 4,
        isOpen = openBottomSheet,
        viewMode = cookingDetailViewModel.viewMode.value,
        onChangeOpenState = { openBottomSheet = it },
        onChangeViewMode = cookingDetailViewModel::changeViewMode
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StandardToolbar(
            navController = navController,
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

            }

        )

        Spacer(modifier = Modifier.height(SpaceLarge))


        val mediaPagerState = rememberPagerState{recipe.instructions.size}


        HorizontalPager(
            state = mediaPagerState,
            contentPadding = PaddingValues(horizontal = SpaceHuge),
            pageSpacing = SpaceMedium,
//            flingBehavior = fling

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
                if (recipe.instructions[page].type is MediaType.Image) {
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

        LaunchedEffect(mediaPagerState.currentPage) {
            cookingDetailViewModel.setTimer(recipe.instructions[mediaPagerState.currentPage].period)
        }
        Spacer(modifier = Modifier.height(SpaceLarge))

        AnimatedContent(
            targetState = mediaPagerState.currentPage,
            label = "instruction",
            transitionSpec = { ->
                slideInHorizontally { if (it > 0) it else -it } with slideOutHorizontally { -it }
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
                cookingDetailViewModel.buttonSelection()
            }
        )
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
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
            )
            Text(
                text = "${mediaPagerState.currentPage + 1} of ${recipe.instructions.size}",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
            )
        }

        StandardProgressIndicator(indicatorProgress = (mediaPagerState.currentPage + 1).toFloat() / recipe.instructions.size)

        Button(
            onClick = {
                if ((mediaPagerState.currentPage + 1) != recipe.instructions.size) {
                    corountineScope.launch {
                        mediaPagerState.animateScrollToPage(mediaPagerState.currentPage + 1)
                    }
                } else {
                    navController.navigate(Screen.RecipeDetailScreen.route) {
                        launchSingleTop = true
                        popUpTo(Screen.CookingDetailScreen.route) {
                            inclusive = true
                        }
                    }
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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

        ) {
            Text(
                text = if ((mediaPagerState.currentPage + 1) != recipe.instructions.size) stringResource(
                    R.string.next_step
                ) else stringResource(
                    R.string.completed
                ),
                style = MaterialTheme.typography.bodyMedium.copy(TrueWhite),
                modifier = Modifier.padding(vertical = SpaceSmall)
            )
        }

    }


}

