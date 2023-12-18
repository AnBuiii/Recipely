package com.anbui.recipely.feature.recipe_detail.cooking_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.ThinGreen
import com.anbui.recipely.feature.recipe_detail.R
import com.anbui.recipely.feature.recipe_detail.convertSecondsToHMmSs
import com.anbui.recipely.feature.recipe_detail.TimerStatus


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Timer(
    modifier: Modifier = Modifier,
    totalTime: Long,
    inactiveBarColor: Color = ThinGreen,
    activeBarColor: Color = MaterialTheme.colorScheme.secondary,
    strokeWidth: Dp = 6.dp,
    currentTime: Long,
    onButtonClick: () -> Unit,
    buttonState: TimerStatus
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(140.dp)
                .onSizeChanged {
                    size = it
                }
        ) {
            Canvas(modifier = modifier.size(140.dp)) {
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = activeBarColor,
                    startAngle = -90f,
                    sweepAngle = 360f * ((totalTime - currentTime) / totalTime.toFloat()),
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }

            FilledIconButton(
                onClick = onButtonClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = when (buttonState) {
                        TimerStatus.INIT, TimerStatus.PAUSED -> {
                            painterResource(id = R.drawable.ic_play)
                        }

                        TimerStatus.RUNNING -> {
                            painterResource(id = R.drawable.ic_pause)
                        }

                        TimerStatus.FINISHED -> {
                            painterResource(id = R.drawable.ic_restart)
                        }
                    },

                    contentDescription = "button",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        AnimatedCounter(count = (currentTime / 1000L).convertSecondsToHMmSs())
    }
}

@ExperimentalAnimationApi
@Composable
fun AnimatedCounter(
    count: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.headlineLarge
) {
    var oldCount by remember {
        mutableStateOf(count)
    }
    SideEffect {
        oldCount = count
    }
    Row(modifier = modifier) {
        val oldCountString = oldCount
        for (i in count.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = count[i]
            val char = if (oldChar == newChar) {
                oldCountString[i]
            } else {
                count[i]
            }
            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                },
                label = stringResource(R.string.animated_counter)
            ) { char ->
                Text(
                    text = char.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}
