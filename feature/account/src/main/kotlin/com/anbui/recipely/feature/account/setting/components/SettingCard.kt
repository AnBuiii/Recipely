package com.anbui.recipely.feature.account.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.components.StandardCard
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.account.R

@Composable
fun SettingCard(
    leadingIcon: Painter,
    text: String,
    trailing: @Composable () -> Unit = {
        FilledIconButton(
            onClick = {},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = TrueWhite
            ),
            modifier = Modifier
                .padding(end = SpaceTiny)
                .size(32.dp),
            shape = MaterialTheme.shapes.medium

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = stringResource(
                    R.string.arrow_right
                )
            )
        }
    },
    onClick: () -> Unit = {}
) {
    StandardCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = leadingIcon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
            }
            trailing()
        }
    }
}
