package com.anbui.recipely.feature.onboard.navigation

sealed class OnboardGraph(val route: String){
    companion object {
        const val ROUTE = "onboard_graph"
    }
    data object Splash: OnboardGraph("splash")
    data object Onboard: OnboardGraph("onboard")
    data object Signup: OnboardGraph("signup")
    data object Login: OnboardGraph("login")
    data object ForgotPassword: OnboardGraph("forgot_password")

}
