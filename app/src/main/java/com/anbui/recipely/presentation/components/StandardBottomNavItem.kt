package com.anbui.recipely.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.util.NoRippleInteractionSource


@ExperimentalMaterial3Api
@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandardBottomNavItem(
    modifier: Modifier = Modifier,
//    icon: @Composable () -> Unit = {},
    contentDescription: String? = "",
    painter: Painter,
    selected: Boolean = false,
    alertCount: Int? = null,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MediumGrey,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (alertCount != null && alertCount < 0) {
        throw IllegalArgumentException("Alert count can't be negative")
    }
    val lineLength = animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        ), label = "animate_line_length"
    )

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedColor,
            unselectedIconColor = unselectedColor,
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
                                color = if (selected) selectedColor
                                else unselectedColor,
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
                        if(alertCount != null){
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
                    Icon(
                        painter = painter,
                        contentDescription = contentDescription,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )
}
