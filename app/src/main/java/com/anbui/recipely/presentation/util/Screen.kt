package com.anbui.recipely.presentation.util

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object AccountScreen : Screen("account_screen")
    data object EditProfileScreen : Screen("edit_profile_screen")
    data object RecipeDetailScreen : Screen("recipe_detail_screen")
    data object CookingDetailScreen : Screen("cooking_detail_screen")

    data object CreateRecipeScreen : Screen("create_recipe_screen")
    data object AddIngredientScreen : Screen("add_ingredient_screen")
    data object AddInstructionScreen : Screen("add_instruction_screen")

    data object CartScreen : Screen("cart_screen")
    data object AddressScreen : Screen("address_screen")
    data object SettingScreen : Screen("setting_screen")

    data object OrderDetailScreen : Screen("order_detail_screen")
    data object CameraScreen : Screen("camera_screen")
}
