package com.anbui.recipely.feature.cart.navigation

sealed class CartGraph(val route: String){
    companion object {
        const val ROUTE = "cart_graph"
    }
    data object Home: CartGraph("cart_graph_home")
    data object Address: CartGraph("address")
}
