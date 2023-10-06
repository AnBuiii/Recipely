package com.anbui.recipely.presentation.recipe.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceSmall

@Composable
fun HeadingSection(
    recipe: Recipe
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceLarge),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = recipe.title,
            softWrap = true,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y = (12).dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = recipe.id,
                tint = MediumGrey
            )
            Text(
                text = recipe.cookTime,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = MediumGrey
                )
            )
        }
    }
    Spacer(modifier = Modifier.padding(top = SpaceSmall))
}
