package com.anbui.recipely.data.repository

import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.dao.OrderDao
import com.anbui.recipely.data.local.entities.OrderEntity
import com.anbui.recipely.data.local.entities.OrderStatusEntity
import com.anbui.recipely.data.local.entities.relations.IngredientAccountCrossRef
import com.anbui.recipely.data.local.entities.relations.OrderIngredientCrossRef
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.Order
import com.anbui.recipely.domain.repository.CartRepository
import com.anbui.recipely.domain.repository.CurrentPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val accountDao: AccountDao,
    private val currentPreferences: CurrentPreferences
) : CartRepository {
    override fun getAllInCartOfCurrentAccount(): Flow<List<IngredientItem>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            orderDao.getIngredientInCart(id ?: "").collect { list ->
                val ingredients = list.map { it.toIngredientItem() }
                emit(ingredients)
            }
        }
    }

    override suspend fun addIngredientToCart(ingredientId: String, amount: Int) {
        val id = currentPreferences.getLoggedId().first() ?: ""
        val exist = orderDao.getIngredientInCartByIdAndAccountId(ingredientId, id)
        if (exist.isEmpty()) {
            val uuid = UUID.randomUUID()

            orderDao.insertToCart(
                IngredientAccountCrossRef(
                    id = uuid.toString(),
                    ingredientId = ingredientId,
                    accountId = id,
                    amount = amount.toFloat(),
                )
            )
        }
    }

    override suspend fun updateAmountInCartOfCurrentAccount(ingredientId: String, amount: Int) {
        val id = currentPreferences.getLoggedId().first() ?: ""
        if (amount > 0) {
            orderDao.updateAmountInCart(ingredientId, id, amount)
        } else {
            orderDao.deleteFromCart(ingredientId, id)
        }
    }

    override fun getAllOrderOfCurrentAccount(): Flow<List<Order>> {
        return flow {
            val id = currentPreferences.getLoggedId().first() ?: ""
            orderDao.getAllOrder(id).collect { list ->
                val orders = list.map {
                    it.toOrder()
                }
                emit(orders)
            }
        }
    }

    override fun getDummyOrder(): Order {
        return Order(
            id = "", time = LocalDateTime.now(), ingredients = listOf(), orderStatuses = listOf(),
            currentStatus = "",
            total = 0.0f,
            customerName = "",
            deliveryInfo = "",
        )
    }

    override suspend fun createOrder() {
        val accountId = currentPreferences.getLoggedId().firstOrNull() ?: return
        val account = accountDao.getAccountById(accountId).first()
        val now = LocalDateTime.now()
        val orderId = UUID.randomUUID().toString().substringBefore("-")

        OrderEntity(
            id = orderId,
            accountId = accountId,
            deliveryInfo = account.getAddress(),
            time = now
        ).let {
            orderDao.insertOrder(it)
        }

        OrderStatusEntity(
            id = UUID.randomUUID().toString(),
            step = "Init",
            time = now,
            orderId = orderId
        ).let {
            orderDao.insertOrderStatus(it)
        }

        orderDao.getInCart(accountId).first().map {
            orderDao.deleteFromCart(it.ingredientId, accountId)
            OrderIngredientCrossRef(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                ingredientId = it.ingredientId,
                amount = it.amount
            )
        }.let {
            orderDao.insertOrderDetails(it)
        }
    }

    override fun getOrderById(orderId: String): Flow<Order> {
        return orderDao.getOrderById(orderId = orderId).map {
            it.toOrder()
        }
    }
}