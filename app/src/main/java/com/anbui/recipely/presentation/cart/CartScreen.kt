package com.anbui.recipely.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.exampleIngredientItems
import com.anbui.recipely.presentation.cart.components.CartItem
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.ThinGrey
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@ExperimentalMaterial3Api
@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.my_cart),
            showBackArrow = true,
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(exampleIngredientItems, key = { it.ingredientId }) {
                    CartItem(
                        imageUrl = it.imageUrl,
                        name = it.name,
                        amount = it.amount,
                        unit = it.unit.toString(),
                        price = 0.39f,
                        modifier = Modifier.padding(vertical = SpaceMedium, horizontal = SpaceLarge)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .shadow(
                        elevation = 16.dp,
                        spotColor = ThinGrey,
                        ambientColor = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.large
                    )
                    .background(TrueWhite, shape = MaterialTheme.shapes.large),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SpaceLarge, vertical = SpaceSmall),
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = stringResource(
                            R.string.location
                        )
                    )
                    Text(
                        text = "134 Lê Chân, Sơn Trà District, Đà Nẵng",
                        maxLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            color = DarkGrey
                        )
                    )
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SpaceLarge, vertical = SpaceSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.total_price),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "\$67.29",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Button(
                        onClick = {
                        },
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .fillMaxWidth(.7f)

                    ) {
                        Text(
                            text = "Checkout",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                            modifier = Modifier.padding(vertical = SpaceSmall)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = SpaceSmall))

            }
        }


    }
}