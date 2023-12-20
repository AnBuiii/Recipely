package com.anbui.recipely.feature.cart.cart.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardCard
import com.anbui.recipely.core.designsystem.theme.SpaceExtraLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.cart.R
import kotlin.math.roundToInt

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    amount: Int,
    price: Float,
    containerColor: Color = TrueWhite,
    onChangeAmount: (Int) -> Unit
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
                    Text(
                        text = "${(price * 100.0).roundToInt() / 100.0} $",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface
//                            fontWeight = FontWeight.Normal
                        ),
                        softWrap = true
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = SpaceSmall),
                horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedIconButton(
                    onClick = {
                        onChangeAmount(amount - 1)
                    },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.size(16.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = stringResource(
                            R.string.negative
                        ),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = amount.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.sizeIn(
                        maxWidth = SpaceExtraLarge,
                        minWidth = SpaceExtraLarge
                    )
                )
                OutlinedIconButton(
                    onClick = {
                        onChangeAmount(amount + 1)
                    },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.size(16.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = stringResource(R.string.plus),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}
