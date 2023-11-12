package com.anbui.recipely.domain.models

data class Order(
    val id: String,
    val formattedTime: String,
    val ingredients: List<IngredientItem>,
    val orderStatuses: List<OrderStatus>,
    val currentStatus: String,
    val total: Float,
    val customerName: String,
    val deliveryInfo: String,
)

