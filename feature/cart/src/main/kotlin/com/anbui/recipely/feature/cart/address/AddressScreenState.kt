package com.anbui.recipely.feature.cart.address

data class AddressScreenState(
    val street: String = "",
    val district: String = "",
    val province: String = "",
    val changeSuccess: Boolean? = null,
)