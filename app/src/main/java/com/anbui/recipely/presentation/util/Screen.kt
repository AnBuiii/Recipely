package com.anbui.recipely.presentation.util

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash_screen")
    data object OnBoardingScreen : Screen("onboarding_screen")

    data object HomeScreen : Screen("home_screen")
    data object SearchScreen : Screen("search_screen")
    data object NotificationScreen : Screen("notification_screen")
    data object AccountScreen : Screen("account_screen")

    data object LoginScreen : Screen("login_screen")
    data object CreateAccountScreen : Screen("create_account_screen")
    data object ForgotPasswordScreen : Screen("forgot_password_screen")
    data object SelectInterestScreen : Screen("select_interest_screen")
    data object EditProfileScreen : Screen("edit_profile_screen")
    data object RecipeDetailScreen : Screen("recipe_detail_screen")
    data object CookingDetailScreen : Screen("cooking_detail_screen")
    data object CreateRecipeScreen : Screen("create_recipe_screen")
    data object AddIngredientScreen : Screen("add_ingredient_screen")

    data object CartScreen : Screen("cart_screen")
    data object AddressScreen : Screen("address_screen")
    data object SettingScreen : Screen("setting_screen")

    data object OrderDetailScreen : Screen("order_detail_screen")
    data object CameraScreen : Screen("camera_screen")

    data object PostDetailScreen : Screen("post_detail_screen")
    data object ChatScreen : Screen("chat_screen")
    data object MessagesScreen : Screen("messages_screen")

    data object PersonListScreen : Screen("person_list_screen")
}
