package com.anbui.recipely.feature.onboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anbui.recipely.feature.onboard.create_account.SignupRoute
import com.anbui.recipely.feature.onboard.forgot_password.ForgotPasswordRoute
import com.anbui.recipely.feature.onboard.login.LoginRoute
import com.anbui.recipely.feature.onboard.onboard.OnBoardRoute
import com.anbui.recipely.feature.onboard.splash.SplashRoute

fun NavController.navigateToOnboard(navOptions: NavOptions? = null) {
    this.navigate(OnboardGraph.ROUTE, navOptions)
}

fun NavGraphBuilder.onBoardGraph(
    onHome: () -> Unit,
    navController: NavController,
) {
    navigation(route = OnboardGraph.ROUTE, startDestination = OnboardGraph.Splash.route) {
        composable(route = OnboardGraph.Splash.route) {
            SplashRoute(
                onTimeout = { logged ->
                    if (logged) {
                        onHome()
                    } else {
                        navController.navigate(OnboardGraph.Onboard.route) {
                            popUpTo(OnboardGraph.Splash.route) {
                                inclusive = true
                            }
                        }
                    }

                }
            )
        }
        composable(route = OnboardGraph.Login.route) {
            LoginRoute(
                onNavigateToHome = onHome,
                onForgotPassword = {
                    navController.navigate(OnboardGraph.ForgotPassword.route)
                },
                onBack = navController::popBackStack
            )
        }

        composable(route = OnboardGraph.ForgotPassword.route) {
            ForgotPasswordRoute(
                onNavigateHome = onHome,
                onBack = navController::popBackStack
            )
        }

        composable(route = OnboardGraph.Signup.route){
            SignupRoute(
                onNavigateHome = onHome,
                onBack = navController::popBackStack
            )
        }

        composable(route = OnboardGraph.Onboard.route){
            OnBoardRoute(
                onCreateAccount = {
                    navController.navigate(OnboardGraph.Signup.route)
                },
                onLogin = {
                    navController.navigate(OnboardGraph.Login.route)
                }
            )
        }
    }

}