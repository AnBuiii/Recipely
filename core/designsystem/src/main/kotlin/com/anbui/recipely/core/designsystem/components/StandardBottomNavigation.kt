package com.anbui.recipely.core.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.R
import com.anbui.recipely.core.designsystem.theme.MediumGrey
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.core.designsystem.toPx
import kotlin.math.sqrt

@Composable
fun StandardBottomNavigation(
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    onNewRecipeClick: () -> Unit = {},
    onScanClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
//            .height(128.dp)
        contentAlignment = Alignment.BottomCenter
    ) {
        var isMenuExtended by remember { mutableStateOf(false) }
        val renderEffect = getRenderEffect().asComposeRenderEffect()
        val fabAnimationProgress by animateFloatAsState(
            targetValue = if (isMenuExtended) 1f else 0f,
            animationSpec = tween(
                durationMillis = 800,
                easing = LinearEasing
            ),
            label = ""
        )
        val clickAnimationProgress by animateFloatAsState(
            targetValue = if (isMenuExtended) 1f else 0f,
            animationSpec = tween(
                durationMillis = 400,
                easing = LinearEasing
            ),
            label = ""
        )

        AnimatedVisibility(visible = isMenuExtended, enter = fadeIn(), exit = fadeOut()) {
            Scrim(
                modifier = Modifier.fillMaxSize(),
                onClose = { isMenuExtended = !isMenuExtended })
        }

        ElevatedCard(
            modifier = Modifier
                .shadow(
                    elevation = 25.dp,
                    spotColor = MediumGrey,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    shape = BottomNavigationShape(16.dp.toPx())
                )
                .fillMaxWidth()
                .height(96.dp)
                .align(Alignment.BottomCenter),
            shape = BottomNavigationShape(16.dp.toPx()),
            colors = CardDefaults.elevatedCardColors(
                containerColor = TrueWhite
            )
//        elevation = CardDefaults.elevatedCardElevation(
//            defaultElevation = 1.dp
//        )

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(windowInsets),
                content = content
            )
        }

        FabGroup(
            renderEffect = renderEffect,
            animationProgress = fabAnimationProgress
        )
        FabGroup(
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = {
                isMenuExtended = !isMenuExtended
            },
            onScanClick = onScanClick,
            onNewRecipeClick = onNewRecipeClick
        )
    }
}

class BottomNavigationShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val circleRadius = 40.dp.toPx()
        val triangleHeight = circleRadius * sqrt(3f)
        val spaceLeft = size.width / 2 - triangleHeight - circleRadius
        val path = Path().apply {
            reset()
            // Corner left
            moveTo(x = 0f, y = cornerRadius)
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = cornerRadius * 2,
                    bottom = cornerRadius * 2
                ),
                startAngleDegrees = -180.0f,
                sweepAngleDegrees = 90.0f,
                forceMoveTo = false
            )
            // Middle left
            lineTo(x = size.width / 2 - triangleHeight, y = 0f)
            // Left arc
            arcTo(
                rect = Rect(
                    left = spaceLeft,
                    top = 0f,
                    right = spaceLeft + circleRadius * 2,
                    bottom = circleRadius * 2
                ),
                startAngleDegrees = -90f,
                sweepAngleDegrees = 60f,
                forceMoveTo = false
            )

            // Middle arc
            arcTo(
                rect = Rect(
                    left = size.width / 2 - circleRadius,
                    top = -circleRadius,
                    right = size.width / 2 + circleRadius,
                    bottom = circleRadius
                ),
                startAngleDegrees = 150f,
                sweepAngleDegrees = -120f,
                forceMoveTo = false
            )

            // Right arc

            arcTo(
                rect = Rect(
                    left = size.width - spaceLeft - circleRadius * 2,
                    top = 0f,
                    right = size.width - spaceLeft,
                    bottom = circleRadius * 2
                ),
                startAngleDegrees = -150f,
                sweepAngleDegrees = 60f,
                forceMoveTo = false
            )

            lineTo(x = size.width - cornerRadius, y = 0f)
            arcTo(
                rect = Rect(
                    left = size.width - cornerRadius * 2,
                    top = 0f,
                    right = size.width,
                    bottom = cornerRadius * 2
                ),
                startAngleDegrees = -90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f, y = cornerRadius)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun Scrim(onClose: () -> Unit, modifier: Modifier = Modifier) {
    val strClose = stringResource(R.string.close)
    Box(
        modifier
            .pointerInput(onClose) { detectTapGestures { onClose() } }
            .semantics(mergeDescendants = true) {
                contentDescription = strClose
                onClick {
                    onClose()
                    true
                }
            }
            .onKeyEvent {
                if (it.key == Key.Escape) {
                    onClose()
                    true
                } else {
                    false
                }
            }
            .background(Color.DarkGray.copy(alpha = 0.75f))
    )
}
