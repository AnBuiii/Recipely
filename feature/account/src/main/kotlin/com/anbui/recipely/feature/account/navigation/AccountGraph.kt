package com.anbui.recipely.feature.account.navigation

sealed class AccountGraph(val route: String) {
    companion object {
        const val ROUTE = "account_graph"
        const val ORDER_ARG = "orderId"
    }

    data object Home : AccountGraph("account_graph_home")
    data object Setting : AccountGraph("setting")
    data object EditProfile : AccountGraph("edit_profile")
    data object MyRecipe : AccountGraph("my_recipe")
    data object MyOrder : AccountGraph("my_order")
    data object OrderDetail : AccountGraph("order_detail/{$ORDER_ARG}")
}
