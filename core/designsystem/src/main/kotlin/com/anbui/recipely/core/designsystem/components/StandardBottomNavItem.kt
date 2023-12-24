package com.anbui.recipely.core.designsystem.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.NoRippleInteractionSource
import com.anbui.recipely.core.designsystem.theme.MediumGrey
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.TrueWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandardBottomNavItem(
    modifier: Modifier = Modifier,
    contentDescription: String? = "",
    unselectedPainter: Painter,
    selectedPainter: Painter,
    selected: Boolean = false,
    alertCount: Int? = null,
    selectedColor: Color = MaterialTheme.colorScheme.secondary,
    unselectedColor: Color = MediumGrey,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (alertCount != null) {
        require(alertCount < 0) {
            throw IllegalArgumentException("Alert count can't be negative")
        }
    }

    val lineLength = animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        ),
        label = "animate_line_length"
    )

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = TrueWhite,
            selectedIconColor = selectedColor,
            unselectedIconColor = unselectedColor
//            indicatorColor = MaterialTheme.colorScheme.surface
        ),
        interactionSource = NoRippleInteractionSource(),
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceLarge)
                    .drawBehind {
                        if (lineLength.value > 0f) {
                            drawLine(
                                color = if (selected) {
                                    selectedColor
                                } else {
                                    unselectedColor
                                },
                                start = Offset(
                                    size.width / 2f - lineLength.value * 15.dp.toPx(),
                                    size.height
                                ),
                                end = Offset(
                                    size.width / 2f + lineLength.value * 15.dp.toPx(),
                                    size.height
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
            ) {
                BadgedBox(
                    badge = {
                        if (alertCount != null) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                Text(
                                    alertCount.toString(),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    AnimatedContent(targetState = selected, label = "") {
                        if (it) {
                            Icon(
                                painter = selectedPainter,
                                contentDescription = contentDescription,
                                modifier = Modifier.align(Alignment.Center),
                                tint = Color.Unspecified
                            )
                        } else {
                            Icon(
                                painter = unselectedPainter,
                                contentDescription = contentDescription,
                                modifier = Modifier.align(Alignment.Center),
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            }
        }
    )
}
