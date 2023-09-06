package com.anbui.recipely.presentation.create_recipe.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Step
import com.anbui.recipely.presentation.components.StandardCard
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun InstructionDragDropList(
    items: List<Step>,
    onMove: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    onAddInstructionClick: () -> Unit
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

                        if (overScrollJob?.isActive == true)
                            return@detectDragGesturesAfterLongPress

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
        itemsIndexed(items, key = { _, item -> item.order }) { index, item ->
            val isDragging = index == dragDropListState.currentIndexOfDraggedItem
            val color = animateColorAsState(
                if (isDragging) MaterialTheme.colorScheme.primary else TrueWhite,
                label = "",
            )
            val textColor = animateColorAsState(
                if (isDragging) TrueWhite else MaterialTheme.colorScheme.primary,
                label = "",
            )

            StandardCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SpaceMedium)
                    .animateItemPlacement()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${item.order}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary),
                                modifier = Modifier
                            )
                        }
                    }
                    Text(
                        text = item.instruction,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            color = DarkGrey
                        )
                    )

                }
            }
        }

        item("add ingredients") {
            Button(
                onClick = onAddInstructionClick,
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




















