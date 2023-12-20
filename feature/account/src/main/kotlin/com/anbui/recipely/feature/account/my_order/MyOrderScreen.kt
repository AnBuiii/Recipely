package com.anbui.recipely.feature.account.my_order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.feature.account.R
import com.anbui.recipely.feature.account.account.components.OrderItem

@Composable
fun MyOrderRoute(
    onBack: () -> Unit,
    onNavigateToOrder: (String) -> Unit,
    myOrderViewModel: MyOrderViewModel = hiltViewModel()
) {
    MyRecipeScreen(
        onBack = onBack,
        onNavigateToOrder = onNavigateToOrder,
        viewModel = myOrderViewModel
    )
}

@Composable
fun MyRecipeScreen(
    onBack: () -> Unit,
    onNavigateToOrder: (String) -> Unit,
    viewModel: MyOrderViewModel
) {
    val orders by viewModel.currentOrders.collectAsStateWithLifecycle()
    Column {
        StandardToolbar(
            title = stringResource(R.string.my_order),
            onBack = onBack,
            showBackArrow = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceLarge)
        ) {
            items(orders, key = { it.id }) { order ->
                OrderItem(
                    order = order,
                    onClick = {
                        onNavigateToOrder(order.id)
                    }
                )
            }
        }
    }
}