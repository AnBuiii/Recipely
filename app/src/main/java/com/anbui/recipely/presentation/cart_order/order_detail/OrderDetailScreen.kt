package com.anbui.recipely.presentation.cart_order.order_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.presentation.cart_order.order_detail.components.InformationItem
import com.anbui.recipely.presentation.cart_order.order_detail.components.OrderItem
import com.anbui.recipely.presentation.ui.components.StandardCard
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.components.Stepper
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.LightGrey
import com.anbui.recipely.presentation.ui.theme.SpaceExtraLarge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.util.toStringAsFixed

@ExperimentalMaterial3Api
@Composable
fun OrderDetailScreen(
    navController: NavController,
    orderDetailViewModel: OrderDetailViewModel = hiltViewModel()
) {
    val order by orderDetailViewModel.order.collectAsStateWithLifecycle()
    Column {
        StandardToolbar(
            navController = navController,
            title = "Order",
            showBackArrow = true
        )
        Box {
            LazyColumn {
                // information section
                item {
                    Text(
                        text = "General information",
                        modifier = Modifier.padding(start = SpaceLarge, bottom = SpaceMedium),
                        style = MaterialTheme.typography.bodyLarge.copy()
                    )
                    StandardCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpaceLarge)
                    ) {
                        InformationItem(headline = "ID", information = order.id)
                        InformationItem(headline = "Date", information = order.time.toString())
                        InformationItem(headline = "Status", information = order.currentStatus)
                        InformationItem(
                            headline = "Total",
                            information = "\$${order.total.toDouble().toStringAsFixed(2)}"
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceMedium
                        ),
                        color = LightGrey
                    )
                }
                item {
                    Text(
                        text = "Delivery to",
                        modifier = Modifier.padding(start = SpaceLarge, bottom = SpaceMedium),
                        style = MaterialTheme.typography.bodyLarge.copy()
                    )
                    StandardCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpaceLarge)
                    ) {
                        Text(
                            text = order.customerName,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = order.deliveryInfo,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = DarkGrey
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceMedium
                        ),
                        color = LightGrey
                    )
                }
                item {
                    Text(
                        text = "Order Status",
                        modifier = Modifier.padding(start = SpaceLarge, bottom = SpaceMedium),
                        style = MaterialTheme.typography.bodyLarge.copy()
                    )
                    StandardCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SpaceLarge)
                    ) {
                        Spacer(modifier = Modifier.height(SpaceLarge))
                        Stepper(
                            numberOfSteps = order.orderStatuses.size,
                            stepDescriptionList = order.orderStatuses.map { it.title }
                        )
                    }
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceMedium
                        ),
                        color = LightGrey
                    )
                }
                item {
                    Text(
                        text = "Items",
                        modifier = Modifier.padding(start = SpaceLarge, bottom = SpaceMedium),
                        style = MaterialTheme.typography.bodyLarge.copy()
                    )
                }
                items(order.ingredients, key = { it.ingredientId }) {
                    OrderItem(
                        imageUrl = it.imageUrl,
                        name = it.name,
                        amount = it.amount,
                        unit = it.unit.toString(),
                        price = 0.39f,
                        modifier = Modifier.padding(vertical = SpaceSmall, horizontal = SpaceLarge)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(2.5 * SpaceExtraLarge))
                }
            }
            Text(text = "")
            Button(
                onClick = {
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(SpaceLarge)

            ) {
                Text(
                    text = "Cancel Order",
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }
        }
    }
}
