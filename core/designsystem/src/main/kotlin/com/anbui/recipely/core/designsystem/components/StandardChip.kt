package com.anbui.recipely.core.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TinyGreen
import com.anbui.recipely.core.designsystem.theme.TrueWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardChip(
    title: String,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    FilterChip(
        onClick = onClick,
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = if (selected) TrueWhite else MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(SpaceSmall)

            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = TinyGreen,
            selectedContainerColor = MaterialTheme.colorScheme.secondary
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = TrueWhite
        ),
        selected = selected,
        shape = MaterialTheme.shapes.large
    )
}
