package com.anbui.recipely.presentation.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anbui.recipely.presentation.account.AccountScreen
import com.anbui.recipely.presentation.create_account.CreateAccountScreen
import com.anbui.recipely.presentation.edit_profile.EditProfileScreen
import com.anbui.recipely.presentation.forgot_password.ForgotPasswordScreen
import com.anbui.recipely.presentation.home.HomeScreen
import com.anbui.recipely.presentation.login.LoginScreen
import com.anbui.recipely.presentation.notification.NotificationScreen
import com.anbui.recipely.presentation.onboard.OnBoardingScreen
import com.anbui.recipely.presentation.recipe_detail.RecipeDetailScreen
import com.anbui.recipely.presentation.search.SearchScreen
import com.anbui.recipely.presentation.select_interest.SelectInterestScreen
import com.anbui.recipely.presentation.splash.SplashScreen

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable

fun Navigation(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = Screen.RecipeDetailScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen(navController = navController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(Screen.CreateAccountScreen.route) {
            CreateAccountScreen(navController = navController)
        }
        composable(Screen.SelectInterestScreen.route) {
            SelectInterestScreen(navController = navController)
        }
        composable(Screen.EditProfileScreen.route){
            EditProfileScreen(navController = navController)
        }
        composable(Screen.RecipeDetailScreen.route){
            RecipeDetailScreen(navController = navController)
        }
//        composable(Screen.MainFeedScreen.route) {
//            MainFeedScreen(navController = navController)
//        }
//        composable(Screen.SearchScreen.route) {
//            SearchScreen(navController = navController)
//        }
//        composable(Screen.ActivityScreen.route) {
//            ActivityScreen(navController = navController)
//        }
//        composable(Screen.MessagesScreen.route) {
//            MessagesScreen(navController = navController)
//        }
//        composable(Screen.ProfileScreen.route) {
//            ProfileScreen(navController = navController)
//        }
//        composable(Screen.ChatScreen.route) {
//            ActivityScreen(navController = navController)
//        }
//        composable(Screen.EditProfileScreen.route){
//            EditProfileScreen(navController = navController)
//        }
//        composable(Screen.CreatePostScreen.route){
//            CreatePostScreen(navController = navController)
//        }
//        composable(Screen.PostDetailScreen.route) {
//            PostDetailScreen(
//                navController = navController,
//                post = Post(
//                    username = "An Bui",
//                    imageUrl = "",
//                    profilePictureUrl = "",
//                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
//                            "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
//                            "magna aliquyam erat, sed diam voluptua Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\\n\" +\n" +
//                            "                    \"diam nonumy eirmod tempor invidunt ut labore et dolore \\n\" +\n" +
//                            "                    \"magna aliquyam erat, sed diam voluptua",
//                    likeCount = 17,
//                    commentCount = 7
//                )
//            )
//        }

    }
}