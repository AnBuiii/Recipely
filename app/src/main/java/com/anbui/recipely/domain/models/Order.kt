package com.anbui.recipely.domain.models

import java.time.LocalDateTime

data class Order(
    val id: String,
    val time: LocalDateTime,
    val ingredients: List<IngredientItem>,
    val orderStatuses: List<OrderStatus>,
    val currentStatus: String,
    val total: Float,
    val customerName: String,
    val deliveryInfo: String,
)

