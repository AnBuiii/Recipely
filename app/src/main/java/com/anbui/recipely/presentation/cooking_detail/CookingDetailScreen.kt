package com.anbui.recipely.presentation.cooking_detail

import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.MediaType
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.cooking_detail.components.DetailBottomSheet
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import kotlin.math.absoluteValue

@UnstableApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CookingDetailScreen(
    navController: NavController,
    cookingDetailViewModel: CookingDetailViewModel = hiltViewModel()
) {
    val recipe = exampleRecipes[0]
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    DetailBottomSheet(isOpen = openBottomSheet, onChangeOpenState = { openBottomSheet = it })
    Column(
        modifier = Modifier.fillMaxSize()
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


        val pagerState = rememberPagerState()
//        val fling = PagerDefaults.flingBehavior(
//            state = pagerState,
//            pagerSnapDistance = PagerSnapDistance.atMost(10)
//        )
        HorizontalPager(
            pageCount = recipe.instructions.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = SpaceHuge),
            pageSpacing = SpaceMedium,
//            flingBehavior = fling

        ) { page ->
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
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
                    VideoPlayer(
                        uri = Uri.parse(recipe.instructions[page].mediaUrl),
                        stop =  page != pagerState.currentPage )
                }

            }
        }

    }

}

@UnstableApi
@Composable
fun VideoPlayer(uri: Uri, stop : Boolean) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = true
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            prepare()
            play()
        }
    }
    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                    FrameLayout.LayoutParams(
                        MATCH_PARENT,
                        MATCH_PARENT
                    )
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
    LaunchedEffect(stop){
        if(stop && exoPlayer.isPlaying){
            exoPlayer.pause()
        }
        if(!stop && !exoPlayer.isPlaying){
            println("Whaaattt")
            exoPlayer.play()
        }
    }
}

