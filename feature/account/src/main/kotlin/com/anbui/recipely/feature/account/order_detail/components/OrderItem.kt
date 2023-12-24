package com.anbui.recipely.feature.account.order_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardCard
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.account.R
import kotlin.math.roundToInt

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    amount: Float,
    unit: String,
    price: Float,
    containerColor: Color = TrueWhite,
) {
    StandardCard(
        modifier = modifier,
        containerColor = containerColor
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop,
                    fallback = painterResource(id = R.drawable.ic_fat)
                )
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal
                        ),
                        softWrap = true,
//                        modifier = Modifier.weight(1f),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "x$amount",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Text(
                            text = "${(price * 100.0).roundToInt() / 100.0} $",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            ),
                            softWrap = true
                        )
                    }
                }
            }
        }
    }
}
