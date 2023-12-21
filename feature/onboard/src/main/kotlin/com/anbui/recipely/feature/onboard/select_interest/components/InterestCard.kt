package com.anbui.recipely.feature.onboard.select_interest.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.onboard.Interest

@ExperimentalMaterial3Api
@Composable
fun InterestCard(
    interest: Interest,
    isSelected: Boolean = false,
    onClick: (Boolean) -> Unit
) {
    OutlinedCard(
        modifier = Modifier.padding(SpaceSmall),
        colors = CardDefaults.cardColors(
            containerColor = TrueWhite
        ),
        onClick = {
            onClick(!isSelected)
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        border = if (isSelected) {
            BorderStroke(
                2.dp,
                MaterialTheme.colorScheme.secondary
            )
        } else {
            BorderStroke(2.dp, Color.Transparent)
        },
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SpaceTiny),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(SpaceTiny)
        ) {
            Image(
                painter = painterResource(id = interest.image),
                contentDescription = interest.title,
                modifier = Modifier.size(42.dp)
            )
            Text(text = interest.title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
