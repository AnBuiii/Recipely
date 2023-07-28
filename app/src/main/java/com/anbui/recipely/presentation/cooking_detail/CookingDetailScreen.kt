package com.anbui.recipely.presentation.cooking_detail

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.anbui.recipely.presentation.cooking_detail.components.DetailBottomSheet
import com.anbui.recipely.presentation.cooking_detail.components.Timer
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
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

    DetailBottomSheet(isOpen = openBottomSheet, onChangeOpenState = { openBottomSheet = it })

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


        val mediaPagerState = rememberPagerState()
//        val instructionPagerState = rememberPagerState()

        HorizontalPager(
            pageCount = recipe.instructions.size,
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
        Spacer(modifier = Modifier.height(SpaceLarge))

//        HorizontalPager(
//            pageCount = recipe.instructions.size,
//            state = instructionPagerState,
//            contentPadding = PaddingValues(horizontal = SpaceLarge),
//            pageSpacing = SpaceLarge,
//
//            ) { page ->
//            Text(
//                recipe.instructions[page].instruction,
//                textAlign = TextAlign.Justify,
//                style = MaterialTheme.typography.titleLarge
//            )
//        }

        Spacer(modifier = Modifier.height(SpaceLarge))

        Spacer(modifier = Modifier.height(SpaceLarge))

        Timer(
            totalTime = state.timeDuration,
            modifier = Modifier
                .size(140.dp),
            currentTime = state.remainingTime,
            buttonState = state.status ,
            onButtonClick = {
                cookingDetailViewModel.buttonSelection()
            }
        )


//        val scrollingFollowingPair by remember {
//            derivedStateOf {
//                if (mediaPagerState.isScrollInProgress) {
//                    mediaPagerState to instructionPagerState
//                } else if (instructionPagerState.isScrollInProgress) {
//                    instructionPagerState to mediaPagerState
//                } else null
//            }
//        }
//        LaunchedEffect(scrollingFollowingPair?.first?.currentPageOffsetFraction) {
//
//            val (scrollingState, followingState) = scrollingFollowingPair ?: return@LaunchedEffect
//
//            println(scrollingState.currentPage)
//            println(scrollingState.currentPageOffsetFraction)
//
//            followingState.scrollToPage(
//                scrollingState.currentPage,
//                scrollingState.currentPageOffsetFraction
//            )
//        }

        LaunchedEffect(mediaPagerState.currentPage) {
            cookingDetailViewModel.setTimer(recipe.instructions[mediaPagerState.currentPage].period)
        }


    }


}

