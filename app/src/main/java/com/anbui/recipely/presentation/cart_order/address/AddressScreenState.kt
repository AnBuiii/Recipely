package com.anbui.recipely.presentation.cart_order.address

import com.anbui.model.Account

data class AddressScreenState(
    val account: com.anbui.model.Account? = null,
    val street: String = "",
    val district: String = "",
    val province: String = "",
    val changeSuccess: Boolean? = null,
)