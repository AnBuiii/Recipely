package com.anbui.recipely.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.FeaturedItem
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@Composable
fun FeaturedCard(
    featuredItem: FeaturedItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .size(height = 172.dp, width = 264.dp)
//            .then(modifier)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img_featured_bg),
                contentDescription = stringResource(
                    R.string.featured_background
                ),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            AsyncImage(
                model = featuredItem.image,
                contentDescription = featuredItem.title,
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .offset(y = -SpaceLarge, x = SpaceLarge)
                    .size(152.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(SpaceMedium),
                verticalArrangement = Arrangement.spacedBy(SpaceTiny)
            ) {
                Text(
                    text = featuredItem.title,
                    style = MaterialTheme.typography.bodyLarge.copy(color = TrueWhite)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = featuredItem.ownerImage,
                            contentDescription = featuredItem.title,
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = featuredItem.ownerName,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = TrueWhite
                            )
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = stringResource(
                                R.string.clock
                            ),
                            tint = TrueWhite
                        )
                        Text(
                            text = featuredItem.time,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = TrueWhite
                            )
                        )
                    }

                }
            }
        }

    }
}