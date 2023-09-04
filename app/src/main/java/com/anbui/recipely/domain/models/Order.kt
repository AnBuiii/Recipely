package com.anbui.recipely.domain.models

data class Order(
    val id: String,
    val formattedTime: String,
    val ingredients: List<IngredientItem>,
    val orderStatuses: List<OrderStatus>
)

val exampleOrder = listOf(
    Order(
        id = "321123",
        "12:32 Yesterday",
        exampleIngredientItems,
        exampleOrderStatus
    )
)
