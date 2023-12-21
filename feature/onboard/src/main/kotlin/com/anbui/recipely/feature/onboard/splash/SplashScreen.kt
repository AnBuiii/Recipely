package com.anbui.recipely.feature.onboard.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.feature.onboard.R
import kotlinx.coroutines.delay

const val SPLASH_SCREEN_DURATION = 1500L

@Composable
fun SplashRoute(
    onTimeout: (logged: Boolean) -> Unit
) {
    SplashScreen(
        onTimeout = onTimeout
    )
}

@Composable
fun SplashScreen(
    onTimeout: (logged: Boolean) -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val currentAccount by splashViewModel.currentAccount.collectAsStateWithLifecycle()

    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(true) {
        delay(SPLASH_SCREEN_DURATION)
        currentOnTimeout(currentAccount != null)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = stringResource(R.string.splash),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}
