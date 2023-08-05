package com.anbui.recipely.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object OnBoardingScreen : Screen("onboarding_screen")

    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
    object NotificationScreen : Screen("notification_screen")
    object AccountScreen : Screen("account_screen")

    object LoginScreen : Screen("login_screen")
    object CreateAccountScreen : Screen("create_account_screen")
    object ForgotPasswordScreen : Screen("forgot_password_screen")
    object SelectInterestScreen: Screen("select_interest_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object RecipeDetailScreen : Screen("recipe_detail_screen")
    object CookingDetailScreen : Screen("cooking_detail_screen")
    object CreateRecipeScreen : Screen("create_recipe_screen")
    object AddIngredientScreen : Screen("add_ingredient_screen")


    object PostDetailScreen : Screen("post_detail_screen")
    object ChatScreen : Screen("chat_screen")
    object MessagesScreen : Screen("messages_screen")


    object PersonListScreen : Screen("person_list_screen")


}