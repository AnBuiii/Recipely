package com.anbui.recipely.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import kotlin.math.roundToInt

@Composable
fun Stepper(modifier: Modifier = Modifier,
            numberOfSteps: Int,
            currentStep: Int,
            stepDescriptionList : List<String> = List(numberOfSteps){""},
            unSelectedColor:Color = Color.LightGray,
            selectedColor:Color? = null,
            isRainbow:Boolean = false
) {

    val descriptionList= MutableList(numberOfSteps){""}

    stepDescriptionList.forEachIndexed { index, element ->
        if (index<numberOfSteps)
            descriptionList[index]=element
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        for (step in 1..numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                step = step,
                isCompete = step < currentStep,
                isCurrent = step == currentStep,
                isComplete= step == numberOfSteps,
                isRainbow = isRainbow,
                stepDescription= descriptionList[step-1],
                unSelectedColor=unSelectedColor,
                selectedColor = selectedColor,
            )
        }
    }
}

@Composable
private fun Step(modifier: Modifier = Modifier,
                 step :Int,
                 isCompete: Boolean,
                 isCurrent: Boolean,
                 isComplete:Boolean,
                 isRainbow: Boolean,
                 stepDescription:String,
                 unSelectedColor:Color,
                 selectedColor: Color?,
) {

    val rainBowColor = Brush.linearGradient(
        listOf(
            Color.Magenta,
            Color.Blue,
            Color.Cyan,
            Color.Green,
            Color.Yellow,
            Color.Red,
        )
    )

    val transition = updateTransition(isCompete, label = "")

    val innerCircleColor by transition.animateColor(label = "innerCircleColor") {
        if (it) selectedColor?: MaterialTheme.colorScheme.primary else unSelectedColor
    }
    val txtColor by transition.animateColor(label = "txtColor")
    { if (it || isCurrent) selectedColor?:MaterialTheme.colorScheme.primary else unSelectedColor }

    val color by transition.animateColor(label = "color")
    { if (it || isCurrent) selectedColor?:MaterialTheme.colorScheme.primary else Color.Gray }

    val borderStroke:BorderStroke = if (isRainbow){
        BorderStroke(2.dp, rainBowColor)
    }else{
        BorderStroke(2.dp, color)
    }

    val textSize by remember { mutableStateOf(12.sp) }

    ConstraintLayout(modifier = modifier) {

        val (circle, txt, line) = createRefs()

        Surface(
            shape = CircleShape,
            border = borderStroke,
            color = unSelectedColor,
            modifier = Modifier.size(30.dp).constrainAs(circle) {
                top.linkTo(parent.top)
                bottom.linkTo(line.top)
                start.linkTo(parent.start)
            }
        ) {

            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = step.toString(),
                    color =  Color.Gray,
                    fontSize = 9.sp)
            }
        }

        Text(
            modifier = Modifier.constrainAs(txt) {
                top.linkTo(circle.top)
                start.linkTo(circle.end, margin = SpaceSmall)
                end.linkTo(parent.end)
                bottom.linkTo(circle.bottom)
            },
            fontSize = textSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = stepDescription,
            color  = txtColor,
        )

        if (!isComplete) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()  //fill the max height
                    .width(1.dp).constrainAs(line){
                    top.linkTo(circle.bottom)
                    start.linkTo(circle.start)
                    end.linkTo(circle.end)
                    bottom.linkTo(parent.bottom)
                }


                    .background(Color.Black, shape = VerticalDottedShape(step = 10.dp)),
//                color = innerCircleColor,
//                thickness = 1.dp,
            )
        }
    }
}
private data class VerticalDottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.height / stepPx).roundToInt()
        val actualStep = size.height / stepsCount
        val dotSize = Size(width = size.width,height = actualStep / 2 )
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset( x = 0f,y = i * actualStep),
                    size = dotSize
                )
            )
        }
        close()
    })
}