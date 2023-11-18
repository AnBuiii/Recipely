package com.anbui.recipely.presentation.cart_order.address

import com.anbui.recipely.domain.models.Account

data class AddressScreenState(
    val account: Account? = null,
    val street: String = "",
    val district: String = "",
    val province: String = "",
    val changeSuccess: Boolean? = null,
)