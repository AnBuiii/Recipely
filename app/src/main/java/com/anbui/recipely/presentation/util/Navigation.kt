package com.anbui.recipely.presentation.util

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.anbui.recipely.presentation.auth.create_account.CreateAccountScreen
import com.anbui.recipely.presentation.auth.forgot_password.ForgotPasswordScreen
import com.anbui.recipely.presentation.auth.login.LoginScreen
import com.anbui.recipely.presentation.auth.onboard.OnBoardingScreen
import com.anbui.recipely.presentation.auth.select_interest.SelectInterestScreen
import com.anbui.recipely.presentation.auth.splash.SplashScreen
import com.anbui.recipely.presentation.cart_order.address.AddressScreen
import com.anbui.recipely.presentation.cart_order.cart.CartScreen
import com.anbui.recipely.presentation.cart_order.order_detail.OrderDetailScreen
import com.anbui.recipely.presentation.main_screen.account.AccountScreen
import com.anbui.recipely.presentation.main_screen.home.HomeScreen
import com.anbui.recipely.presentation.main_screen.notification.NotificationScreen
import com.anbui.recipely.presentation.main_screen.search.SearchScreen
import com.anbui.recipely.presentation.other_feature.camera.CameraScreen
import com.anbui.recipely.presentation.other_feature.edit_profile.EditProfileScreen
import com.anbui.recipely.presentation.other_feature.setting.SettingScreen
import com.anbui.recipely.presentation.recipe.cooking_detail.CookingDetailScreen
import com.anbui.recipely.presentation.recipe.create_recipe.CreateRecipeScreen
import com.anbui.recipely.presentation.recipe.create_recipe.CreateRecipeViewModel
import com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_ingredient.AddIngredientScreen
import com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_instruction.AddInstructionScreen
import com.anbui.recipely.presentation.recipe.recipe_detail.RecipeDetailScreen

@ExperimentalAnimationApi
@UnstableApi
@ExperimentalStdlibApi
@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable("${Screen.SearchScreen.route}/{ingredientName}",
            arguments = listOf(
                navArgument("ingredientName") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) {
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
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }
        composable("${Screen.RecipeDetailScreen.route}/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                }
            )
        ) {
            RecipeDetailScreen(navController = navController)
        }
        composable(
            "${Screen.CookingDetailScreen.route}/{recipeId}/{servings}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                },
                navArgument("servings") {
                    type = NavType.IntType
                }

            )
        ) {
            CookingDetailScreen(navController = navController)
        }
        composable(Screen.CartScreen.route) {
            CartScreen(navController = navController)
        }
        navigation(
            route = "create_recipe",
            startDestination = Screen.CreateRecipeScreen.route,
        ) {
            composable(
                Screen.CreateRecipeScreen.route,
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("create_recipe")
                }
                val createRecipeViewModel = hiltViewModel<CreateRecipeViewModel>(parentEntry)
                CreateRecipeScreen(navController = navController, createRecipeViewModel)
            }
        }
        composable(
            "${Screen.AddIngredientScreen.route}?ingredientId={ingredientId}&amount={amount}",
            arguments = listOf(
                navArgument("ingredientId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("amount") {
                    type = NavType.FloatType
                    defaultValue = 0.0
                }
            )
        ) {
            AddIngredientScreen(navController = navController)
        }

        composable(
            "${Screen.AddInstructionScreen.route}?instructionId={instructionId}&instruction={instruction}&period={period}",
            arguments = listOf(
                navArgument("instructionId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("instruction") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("period") {
                    type = NavType.FloatType
                    defaultValue = 0.0
                }
            )
        ) { backStack ->
            val a = backStack.arguments?.getString("instructionId")
            val b = backStack.arguments?.getString("instruction")
            val c = backStack.arguments?.getFloat("period")
            Log.d("instruction screen", "$a, $b, $c")
            AddInstructionScreen(navController = navController)
        }

        composable(Screen.AddressScreen.route) {
            AddressScreen(navController = navController)
        }
        composable(Screen.SettingScreen.route) {
            SettingScreen(navController = navController)
        }
        composable(
            "${Screen.OrderDetailScreen.route}/{orderId}", arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                },
            )
        ) {
            OrderDetailScreen(navController = navController)
        }
        composable(Screen.CameraScreen.route) {
            CameraScreen(navController = navController)
        }

//        navigation(route = "Create Recipe", startDestination = Screen.AddIngredientScreen.route){
//            composable(Screen.CreateRecipeScreen.route) {backStackEntry->
//                val parentEntry = remember(backStackEntry) {
//                    navController.getBackStackEntry("Create Recipe")
//                }
//                val createRecipeViewModel = hiltViewModel<CreateRecipeViewModel>(parentEntry)
//                CreateRecipeScreen(navController = navController, createRecipeViewModel)
//            }
//            composable(Screen.AddIngredientScreen.route) {backStackEntry ->
//                val parentEntry = remember(backStackEntry) {
//                    navController.getBackStackEntry("Create Recipe")
//                }
//                val createRecipeViewModel = hiltViewModel<CreateRecipeViewModel>(parentEntry)
//                AddIngredientScreen(navController = navController
// //                    , createRecipeViewModel
//                )
//            }
//        }

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
