package com.anbui.recipely.core.designsystem.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.anbui.recipely.core.designsystem.R
import com.anbui.recipely.core.designsystem.theme.DarkGrey
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import kotlin.math.roundToInt

@Composable
fun Stepper(
    modifier: Modifier = Modifier,
    numberOfSteps: Int,
    currentStep: Int = 1,
    stepDescriptionList: List<String> = List(numberOfSteps) { "" },
    unSelectedColor: Color = Color.LightGray,
    selectedColor: Color? = null,
    isRainbow: Boolean = false
) {
    val descriptionList = MutableList(numberOfSteps) { "" }

    stepDescriptionList.forEachIndexed { index, element ->
        if (index < numberOfSteps) {
            descriptionList[index] = element
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        for (step in 1..numberOfSteps) {
            Step(
                modifier = if (step != numberOfSteps) Modifier.height(80.dp) else Modifier,
//                modifier =  Modifier.height(60.dp),
                step = step,
                isCompete = step < currentStep,
                isCurrent = step == currentStep,
                isComplete = step == numberOfSteps,
                isRainbow = isRainbow,
                stepDescription = descriptionList[step - 1],
                unSelectedColor = unSelectedColor,
                selectedColor = selectedColor
            )
        }
    }
}

@Composable
private fun Step(
    modifier: Modifier = Modifier,
    step: Int,
    isCompete: Boolean,
    isCurrent: Boolean,
    isComplete: Boolean,
    isRainbow: Boolean,
    stepDescription: String,
    unSelectedColor: Color,
    selectedColor: Color?
) {
    val rainBowColor = Brush.linearGradient(
        listOf(
            Color.Magenta,
            Color.Blue,
            Color.Cyan,
            Color.Green,
            Color.Yellow,
            Color.Red
        )
    )

    val transition = updateTransition(isCompete, label = "")

    val innerCircleColor by transition.animateColor(label = "innerCircleColor") {
        if (it) selectedColor ?: MaterialTheme.colorScheme.primary else unSelectedColor
    }
    val txtColor by transition.animateColor(label = "txtColor") {
        if (it || isCurrent) selectedColor ?: MaterialTheme.colorScheme.primary else unSelectedColor
    }

    val color by transition.animateColor(label = "color") {
        if (it || isCurrent) selectedColor ?: MaterialTheme.colorScheme.primary else Color.Gray
    }

    val borderStroke: BorderStroke = if (isRainbow) {
        BorderStroke(2.dp, rainBowColor)
    } else {
        BorderStroke(2.dp, color)
    }

    val textSize by remember { mutableStateOf(12.sp) }

    ConstraintLayout(modifier = modifier) {
        val (circle, txt, line) = createRefs()

        Icon(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(circle) {
                    top.linkTo(parent.top, if (isComplete) -12.dp else 0.dp)
                    bottom.linkTo(line.top)
                    start.linkTo(parent.start)
                }
                .size(24.dp)
                .background(TrueWhite),
            tint = MaterialTheme.colorScheme.secondary
        )
        Column(
            modifier = Modifier.constrainAs(txt) {
                top.linkTo(circle.top)
                start.linkTo(circle.end, margin = SpaceLarge)
                end.linkTo(parent.end)
                bottom.linkTo(circle.bottom)
            }
        ) {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = stepDescription,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "20:09, Today",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = DarkGrey
                )
            )
        }

        if (!isComplete) {
            Box(
                modifier = Modifier
//                    .height(30.dp)  //fill the max height
                    .fillMaxHeight()
                    .width(1.dp)
                    .constrainAs(line) {
                        top.linkTo(circle.bottom)
                        start.linkTo(circle.start)
                        end.linkTo(circle.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        shape = VerticalDottedShape(step = 10.dp)
                    )
//                color = innerCircleColor,
//                thickness = 1.dp,
            )
        }
    }
}

private data class VerticalDottedShape(
    val step: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        Path().apply {
            val stepPx = with(density) { step.toPx() }
            val stepsCount = (size.height / stepPx).roundToInt()
            val actualStep = size.height / stepsCount
            val dotSize = Size(width = size.width, height = actualStep / 2)
            for (i in 0 until stepsCount) {
                addRect(
                    Rect(
                        offset = Offset(x = 0f, y = i * actualStep),
                        size = dotSize
                    )
                )
            }
            close()
        }
    )
}
