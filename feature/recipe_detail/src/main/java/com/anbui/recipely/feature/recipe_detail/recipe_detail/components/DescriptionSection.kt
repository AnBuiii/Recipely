package com.anbui.recipely.feature.recipe_detail.recipe_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.anbui.recipely.core.designsystem.components.StandardExpandingText
import com.anbui.recipely.core.designsystem.theme.SpaceMedium

fun LazyListScope.descriptionSection(
    description: String,
    isExpanded: Boolean,
    onHintClick: () -> Unit
) {
    item {
        Row {
            StandardExpandingText(
                longText = description,
                isExpanded = isExpanded,
                onHintClick = onHintClick
            )
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
    }
}
