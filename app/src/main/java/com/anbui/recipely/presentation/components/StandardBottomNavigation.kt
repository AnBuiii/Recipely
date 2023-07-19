package com.anbui.recipely.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.toPx
import kotlin.math.sqrt

@Composable
fun StandardBottomNavigation(
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
    ) {
        FilledIconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .shadow(
                    elevation = 40.dp,
                    spotColor = MaterialTheme.colorScheme.secondary,
                    ambientColor = MaterialTheme.colorScheme.secondary,
                )
                .size(56.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_recipely_foreground),
                contentDescription = stringResource(R.string.make_recipe),
//                modifier = Modifier.size(200.dp)
            )
        }
        ElevatedCard(
            modifier = Modifier
                .shadow(
                    elevation = 25.dp,
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    shape = BottomNavigationShape(16.dp.toPx())
                )
                .fillMaxWidth()
                .height(96.dp)
                .align(Alignment.BottomCenter),
            shape = BottomNavigationShape(16.dp.toPx()),
            colors = CardDefaults.elevatedCardColors(
                containerColor = TrueWhite
            ),
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
    }

}


class BottomNavigationShape(private val cornerRadius: Float) : androidx.compose.ui.graphics.Shape {
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
            moveTo(x = cornerRadius, y = 0f)
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

            //Middle arc
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
                    left = size.width - circleRadius * 2,
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