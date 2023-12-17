package com.anbui.recipely.presentation.cart_order.address

import com.anbui.recipely.core.model.Account

data class AddressScreenState(
    val account: Account? = null,
    val street: String = "",
    val district: String = "",
    val province: String = "",
    val changeSuccess: Boolean? = null,
)