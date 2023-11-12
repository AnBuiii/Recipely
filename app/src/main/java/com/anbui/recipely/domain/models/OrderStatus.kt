package com.anbui.recipely.domain.models

data class OrderStatus(
    val id: String?,
    val time: String,
    val title: String,
)

val exampleOrderStatus = listOf(
    OrderStatus(id = "213", "12:47 Yesterday", "Order is processing"),
    OrderStatus(id = "2123", "20:47 Yesterday", "Order is ready to pickup"),
    OrderStatus(id = "676", "22:47 Yesterday", "Order is being delivered"),
    OrderStatus(id = "09230", "8:47 Today", "Successful delivery")
)
