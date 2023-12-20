package com.anbui.recipely.feature.create_recipe.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.feature.create_recipe.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IngredientDragDropList(
    items: List<IngredientItem>,
    onMove: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    onAddIngredientClick: () -> Unit,
    onEditIngredient: (IngredientItem) -> Unit
) {
    val scope = rememberCoroutineScope()
    var overScrollJob by remember { mutableStateOf<Job?>(null) }
    val dragDropListState = rememberDraggableListState(onMove = onMove)

    LazyColumn(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDrag = { change, offset ->
                        println("current ${dragDropListState.currentElement?.index}")
                        change.consume()
                        dragDropListState.onDrag(offset = offset)

                        if (overScrollJob?.isActive == true) {
                            return@detectDragGesturesAfterLongPress
                        }

                        dragDropListState
                            .checkForOverScroll()
                            .takeIf { it != 0f }
                            ?.let {
                                overScrollJob = scope.launch {
                                    dragDropListState.lazyListState.scrollBy(it)
                                }
                            } ?: run { overScrollJob?.cancel() }
                    },
                    onDragStart = { offset -> dragDropListState.onDragStart(offset) },
                    onDragEnd = { dragDropListState.onDragInterrupted() },
                    onDragCancel = { dragDropListState.onDragInterrupted() }
                )
            }
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        state = dragDropListState.lazyListState
    ) {
        itemsIndexed(items, key = { _, item -> item.ingredientId }) { index, item ->
            val isDragging = index == dragDropListState.currentIndexOfDraggedItem
            val color = animateColorAsState(
                if (isDragging) MaterialTheme.colorScheme.primary else TrueWhite,
                label = ""
            )
            val textColor = animateColorAsState(
                if (isDragging) TrueWhite else MaterialTheme.colorScheme.primary,
                label = ""
            )

            IngredientItem(
                modifier = Modifier
                    .padding(vertical = SpaceMedium, horizontal = SpaceLarge)
                    .animateItemPlacement(),
                imageUrl = item.imageUrl,
                name = item.name,
                amount = item.amount,
                unit = item.unit.toString(),
                containerColor = color.value,
                textColor = textColor.value,
                onClick = { onEditIngredient(item) }
            )
        }

        item("add ingredient") {
            Button(
                onClick = onAddIngredientClick,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SpaceLarge, start = SpaceLarge, end = SpaceLarge)

            ) {
                Text(
                    text = stringResource(R.string.add_ingredient),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }
        }
    }
}
