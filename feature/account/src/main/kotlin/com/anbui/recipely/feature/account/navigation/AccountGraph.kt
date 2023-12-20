package com.anbui.recipely.feature.account.navigation

sealed class AccountGraph(val route: String){
    companion object {
        const val ROUTE = "account_graph"
    }
    data object Home: AccountGraph("home")
    data object Setting: AccountGraph("setting")
    data object EditProfile: AccountGraph("edit_profile")
    data object MyRecipe: AccountGraph("my_recipe")
    data object MyOrder: AccountGraph("my_order")
}
