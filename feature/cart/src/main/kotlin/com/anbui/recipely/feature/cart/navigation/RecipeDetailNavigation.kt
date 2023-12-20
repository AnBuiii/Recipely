package com.anbui.recipely.feature.cart.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.anbui.recipely.feature.cart.address.AddressRoute
import com.anbui.recipely.feature.cart.cart.CartRoute
import com.anbui.recipely.feature.cart.CartViewModel

fun NavController.onNavigateToCartGraph(navOptions: NavOptions? = null) {
    this.navigate(CartGraph.ROUTE, navOptions)
}

fun NavGraphBuilder.cartGraph(
    onBack: () -> Unit,
    navController: NavController,
) {
    navigation(
        route = CartGraph.ROUTE,
        startDestination = CartGraph.Home.route,
    ) {

        composable(route = CartGraph.Home.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CartGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<CartViewModel>(parentEntry)
            CartRoute(
                onBack = onBack,
                onNavigateToAddress = {
                    navController.navigate(CartGraph.Address.route) {
                        launchSingleTop = true
                    }
                },
                cartViewModel = parentViewModel
            )

        }
        composable(route = CartGraph.Address.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(CartGraph.ROUTE)
            }
            val parentViewModel = hiltViewModel<CartViewModel>(parentEntry)
            AddressRoute(onBack = onBack, cartViewModel = parentViewModel)
        }
    }

}