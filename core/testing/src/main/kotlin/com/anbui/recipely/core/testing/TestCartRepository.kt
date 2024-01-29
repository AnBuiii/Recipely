package com.anbui.recipely.core.testing

import com.anbui.recipely.core.data.repository.CartRepository
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class TestCartRepository @Inject constructor() : CartRepository {
    override fun getAllInCartOfCurrentAccount(): Flow<List<IngredientItem>> {
        return flow { }
    }

    override fun getAllOrderOfCurrentAccount(): Flow<List<Order>> {
        return flow { }

    }

    override fun getOrderById(orderId: String): Flow<Order> {
        return flow { }

    }

    override fun getDummyOrder(): Order {
        return Order(
            id = "porro",
            time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            ingredients = listOf(),
            orderStatuses = listOf(),
            currentStatus = "erat",
            total = 2.3f,
            customerName = "Jeannette Knight",
            deliveryInfo = "ludus"
        )

    }

    override suspend fun updateAmountInCartOfCurrentAccount(ingredientId: String, amount: Int) {
        // do nothing

    }

    override suspend fun addIngredientToCart(ingredientId: String, amount: Int) {
        // do nothing


    }

    override suspend fun createOrder() {
        // do nothing

    }

    override suspend fun cancelOrder(id: String) {
        // do nothing


    }
}