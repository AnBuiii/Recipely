package com.anbui.recipely.core.data.impl

import com.anbui.recipely.core.database.dao.OrderDao
import com.anbui.recipely.core.database.entities.OrderStatusEntity
import com.anbui.recipely.core.data.repository.CartRepository
import com.anbui.recipely.core.data.repository.NotificationRepository
import com.anbui.recipely.core.database.dao.AccountDao
import com.anbui.recipely.core.database.entities.OrderEntity
import com.anbui.recipely.core.database.entities.IngredientAccountCrossRef
import com.anbui.recipely.core.database.entities.OrderIngredientCrossRef
import com.anbui.recipely.core.datastore.RecipelyPreferencesDataSource
import com.anbui.recipely.core.model.IngredientItem
import com.anbui.recipely.core.model.Notification
import com.anbui.recipely.core.model.NotificationType
import com.anbui.recipely.core.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val accountDao: AccountDao,
    private val notificationRepository: NotificationRepository,
    private val preferencesDataSource: RecipelyPreferencesDataSource
) : CartRepository {
    override fun getAllInCartOfCurrentAccount(): Flow<List<IngredientItem>> {
        return flow {
            val id = preferencesDataSource.loggedId.first()
            orderDao.getIngredientInCart(id ?: "").collect { list ->
                val ingredients = list.map { it.toIngredientItem() }
                emit(ingredients)
            }
        }
    }

    override suspend fun addIngredientToCart(ingredientId: String, amount: Int) {
        val id = preferencesDataSource.loggedId.first()
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
        val id = preferencesDataSource.loggedId.first()
        if (amount > 0) {
            orderDao.updateAmountInCart(ingredientId, id, amount)
        } else {
            orderDao.deleteFromCart(ingredientId, id)
        }
    }

    override fun getAllOrderOfCurrentAccount(): Flow<List<Order>> {
        return flow {
            val id = preferencesDataSource.loggedId.first()
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
            id = "",
            time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            ingredients = listOf(),
            orderStatuses = listOf(),
            currentStatus = "",
            total = 0.0f,
            customerName = "",
            deliveryInfo = "",
        )
    }

    override suspend fun createOrder() {
        val accountId = preferencesDataSource.loggedId.first()
        val account = accountDao.getAccountById(accountId).first()
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
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
            step = "Init", // TODO refactor
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
        Notification(
            id = UUID.randomUUID().toString(),
            userId = accountId,
            notificationType = NotificationType.Order,
            message = "Your order $orderId has been created",
            isRead = false,
            imageUrl = null
        ).let {
            notificationRepository.insertNotification(it)
        }
    }

    override suspend fun cancelOrder(id: String) {
        val accountId = preferencesDataSource.loggedId.first()

        OrderStatusEntity(
            id = UUID.randomUUID().toString(),
            step = "Cancel", // TODO refactor
            time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            orderId = id
        ).let {
            orderDao.insertOrderStatus(it)
        }
        Notification(
            id = UUID.randomUUID().toString(),
            userId = accountId,
            notificationType = NotificationType.Order,
            message = "Your order $id has been cancel",
            isRead = false,
            imageUrl = null
        ).let {
            notificationRepository.insertNotification(it)
        }
    }

    override fun getOrderById(orderId: String): Flow<Order> {
        return orderDao.getOrderById(orderId = orderId).map {
            it.toOrder()
        }
    }
}