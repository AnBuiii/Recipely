package com.anbui.recipely.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.ThinGrey
import com.anbui.recipely.core.designsystem.theme.TrueWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardCard(
    modifier: Modifier = Modifier,
    contentPadding: Dp = SpaceMedium,
    onClick: () -> Unit = {},
    containerColor: Color = TrueWhite,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .shadow(
                elevation = 16.dp,
                spotColor = ThinGrey,
                ambientColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            ),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        onClick = onClick

    ) {
        Column(
            modifier = Modifier
                .padding(contentPadding),
//                .fillMaxSize()
            verticalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}
