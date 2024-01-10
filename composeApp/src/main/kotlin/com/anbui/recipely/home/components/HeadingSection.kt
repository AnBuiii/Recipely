package com.anbui.recipely.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.anbui.recipely.R
import com.anbui.recipely.core.designsystem.theme.SpaceTiny

@Composable
fun HeadingSection(
    name: String,
    onCartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceTiny),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sun),
                    contentDescription = stringResource(
                        R.string.sun
                    ),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    "Good morning",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Text(text = name, style = MaterialTheme.typography.headlineSmall)
        }
        IconButton(onClick = onCartClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_buy),
                contentDescription = stringResource(
                    R.string.buy
                )
            )
        }
    }
}
