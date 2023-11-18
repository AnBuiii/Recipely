package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.AccountEntity
import com.anbui.recipely.data.local.entities.OrderEntity
import com.anbui.recipely.data.local.entities.OrderStatusEntity
import com.anbui.recipely.domain.models.Order
import com.anbui.recipely.domain.models.getTotalPrice

data class OrderWithDetail(
    @Embedded
    val order: OrderEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "order_id",
    )
    val status: List<OrderStatusEntity>,
    @Relation(
        parentColumn = "_id",
        entityColumn = "order_id",
        entity = OrderIngredientCrossRef::class
    )
    val ingredients: List<OrderAndIngredientCrossRef>,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "_id"
    )
    val customer: AccountEntity
) {
    fun toOrder(): Order {
        return Order(
            id = this.order.id,
            time = this.order.time,
            ingredients = this.ingredients.map { it.toIngredientItem() },
            orderStatuses = this.status.map { it.toOrderStatus() },
            currentStatus = this.status.minByOrNull { it.time }?.step ?: "error",
            total = this.ingredients.map { it.toIngredientItem() }.getTotalPrice(),
            customerName = this.customer.lastName + " " + this.customer.firstName,
            deliveryInfo = this.order.deliveryInfo,
        )
    }
}