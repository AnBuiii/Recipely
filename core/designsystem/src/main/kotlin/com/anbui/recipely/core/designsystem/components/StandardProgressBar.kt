package com.anbui.recipely.core.designsystem.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.ThinGreen

@Composable
fun StandardProgressIndicator(
    indicatorProgress: Float
) {
    val progressAnimation by animateFloatAsState(
        targetValue = indicatorProgress,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
        label = ""
    )
    LinearProgressIndicator(
        strokeCap = StrokeCap.Round,
        progress = progressAnimation,
        color = MaterialTheme.colorScheme.secondary,
        trackColor = ThinGreen,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceLarge, vertical = SpaceSmall)
            .height(8.dp)
    )
}
