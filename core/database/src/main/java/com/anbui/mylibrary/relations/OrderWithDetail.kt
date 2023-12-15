package com.anbui.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.database.entities.AccountEntity
import com.anbui.database.entities.OrderEntity
import com.anbui.database.entities.OrderStatusEntity
import com.anbui.model.Order
import com.anbui.model.getTotalPrice

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
    fun toOrder(): com.anbui.model.Order {
        return com.anbui.model.Order(
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