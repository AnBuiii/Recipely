package com.anbui.recipely.domain.models

data class OrderStatus(
    val time: String,
    val title: String,
    val subTitle: String?,
)

val exampleOrderStatus = listOf(
    OrderStatus("12:47 Yesterday", "Order is processing", null),
    OrderStatus("20:47 Yesterday", "Order is ready to pickup", null),
    OrderStatus("22:47 Yesterday", "Order is being delivered", "Đến Hồ Chí Minh"),
    OrderStatus("8:47 Today", "Successful delivery", null),
)
