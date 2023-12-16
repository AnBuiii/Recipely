package com.anbui.recipely.core.designsystem.components

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.R
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun Circle(color: Color, animationProgress: Float) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(44.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = color.copy(alpha = color.alpha * animationValue),
                shape = CircleShape
            )
    )
}

@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { },
    onScanClick: () -> Unit = {},
    onNewRecipeClick: () -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = 64.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // left
        AnimatedFab(
            painter = painterResource(id = R.drawable.ic_scan),
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 64.dp,
                        end = 120.dp
                    ) * FastOutSlowInEasing.transform(0f, 0.8f, animationProgress)
                ),
            opacity = LinearEasing.transform(0.2f, 0.7f, animationProgress),
            shape = RoundedCornerShape(
                (
                        -34 * FastOutSlowInEasing.transform(
                            0f,
                            1f,
                            animationProgress
                        ) + 50
                        ).dp
            ),
            onClick = onScanClick
        )

        // right
        AnimatedFab(
            painter = painterResource(id = R.drawable.ic_cook),
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 64.dp,
                        start = 120.dp
                    ) * FastOutSlowInEasing.transform(0.2f, 1.0f, animationProgress)
                ),
            opacity = LinearEasing.transform(0.4f, 0.9f, animationProgress),
            shape = RoundedCornerShape(
                (
                        -34 * FastOutSlowInEasing.transform(
                            0f,
                            1f,
                            animationProgress
                        ) + 50
                        ).dp
            ),
            onClick = onNewRecipeClick

        )

//        AnimatedFab(
//            modifier = Modifier
//                .scale(1f - LinearEasing.transform(0.5f, 0.85f, animationProgress)),
//        )

        AnimatedFab(
            painter = painterResource(id = R.drawable.ic_recipely),
            modifier = Modifier
                .rotate(
                    360 * FastOutSlowInEasing
                        .transform(0.35f, 0.65f, animationProgress)
                ),
            onClick = toggleAnimation
//            shape = CircleShape
//            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = CircleShape,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(2.dp),
        containerColor = backgroundColor,
        modifier = modifier,
        shape = shape
//            .scale(1.25f)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    painter: Painter? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = CircleShape,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(2.dp),
        containerColor = backgroundColor,
        modifier = modifier,
        shape = shape
//            .scale(1.25f)
    ) {
        painter?.let {
            Icon(
                painter = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}

fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

fun Easing.transform(from: Float, to: Float, value: Float): Float {
    return transform(((value - from) * (1f / (to - from))).coerceIn(0f, 1f))
}

operator fun PaddingValues.times(value: Float): PaddingValues = PaddingValues(
    top = calculateTopPadding() * value,
    bottom = calculateBottomPadding() * value,
    start = calculateStartPadding(LayoutDirection.Ltr) * value,
    end = calculateEndPadding(LayoutDirection.Ltr) * value
)
