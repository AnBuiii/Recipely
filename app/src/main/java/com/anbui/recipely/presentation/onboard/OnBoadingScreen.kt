package com.anbui.recipely.presentation.onboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.OnBoardingItem
import com.anbui.recipely.presentation.components.HorizontalPagerIndicator
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val pageState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val onBoardings = listOf(
        OnBoardingItem(
            title = stringResource(R.string.onboarding_title_1),
            subtitle = stringResource(R.string.onboarding_subtitle_1),
            img = R.drawable.img_illustration_1
        ),
        OnBoardingItem(
            title = stringResource(R.string.onboarding_title_2),
            subtitle = stringResource(R.string.onboarding_subtitle_2),
            img = R.drawable.img_illustration_2
        ),
        OnBoardingItem(
            title = stringResource(R.string.onboarding_title_3),
            subtitle = stringResource(R.string.onboarding_subtitle_3),
            img = R.drawable.img_illustration_3
        )
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        HorizontalIn
        Image(
            painter = painterResource(id = R.drawable.img_background_white),
            contentDescription = stringResource(
                R.string.background
            ),
            modifier = Modifier.fillMaxSize()
        )

        HorizontalPager(
            pageCount = onBoardings.size,
            state = pageState,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = onBoardings[it].img),
                    contentDescription = stringResource(
                        R.string.onboarding_image, it
                    ),
                )
                Spacer(
                    modifier = Modifier.height(SpaceHuge)
                )
                Text(
                    text = onBoardings[it].title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = SpaceLarge)
                )
                Spacer(
                    modifier = Modifier.height(SpaceMedium)
                )
                Text(
                    text = onBoardings[it].subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MediumGrey,
                    modifier = Modifier.padding(horizontal = SpaceLarge)
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            pageCount = onBoardings.size,
            modifier = Modifier.padding(top = SpaceHuge * 3)
        )
        AnimatedVisibility(
            enter = fadeIn(),
            exit = fadeOut(),
            visible = pageState.currentPage != onBoardings.size - 1,
            modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .padding(top = SpaceHuge, end = SpaceMedium)
        ) {
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        pageState.animateScrollToPage(
                            onBoardings.size - 1
                        )
                    }
                },
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = SpaceHuge, start = SpaceLarge, end = SpaceLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = pageState.currentPage == onBoardings.size - 1,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                TextButton(
                    onClick = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                ) {
                    Text(
                        text = stringResource(R.string.create_new_account),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            Button(
                onClick = {
                    if (pageState.currentPage != onBoardings.size - 1) {
                        coroutineScope.launch {
                            pageState.animateScrollToPage(pageState.currentPage + 1)
                        }
                    } else {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = if (pageState.currentPage != onBoardings.size - 1) stringResource(R.string.next) else stringResource(
                        R.string.login
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }
        }

    }
}