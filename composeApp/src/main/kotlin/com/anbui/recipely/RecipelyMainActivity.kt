package com.anbui.recipely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.anbui.recipely.core.designsystem.BottomNavItem
import com.anbui.recipely.core.designsystem.components.StandardScaffold
import com.anbui.recipely.core.designsystem.theme.RecipelyTheme
import com.anbui.recipely.feature.account.navigation.AccountGraph
import com.anbui.recipely.feature.create_recipe.navigation.navigateToCreateRecipe
import com.anbui.recipely.feature.notification.navigation.notificationRoute
import com.anbui.recipely.feature.search.navigation.searchRoute
import com.anbui.recipely.home.navigation.homeRoute
import com.example.ingredient_detect.navigation.navigateToCameraGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipelyMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RecipelyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val bottomNavItems: List<BottomNavItem> = listOf(
                        BottomNavItem(
                            route = homeRoute,
                            unselectedIcon = R.drawable.ic_home,
                            selectedIcon = R.drawable.ic_home_filled,
                            contentDescription = stringResource(R.string.home)
                        ),
                        BottomNavItem(
                            "$searchRoute/ ",
                            unselectedIcon = R.drawable.ic_search,
                            selectedIcon = R.drawable.ic_search_filled,
                            contentDescription = stringResource(R.string.search)
                        ),
                        BottomNavItem(
                            route = "",
                            unselectedIcon = null,
                            selectedIcon = R.drawable.ic_search_filled,
                            contentDescription = stringResource(R.string.search)
                        ),
                        BottomNavItem(
                            route = notificationRoute,
                            selectedIcon = R.drawable.ic_notification_filled,
                            unselectedIcon = R.drawable.ic_notification,
                            contentDescription = stringResource(R.string.notifications)
                        ),
                        BottomNavItem(
                            route = AccountGraph.ROUTE,
                            selectedIcon = R.drawable.ic_profile_filled,
                            unselectedIcon = R.drawable.ic_profile,
                            contentDescription = stringResource(R.string.account)
                        )
                    )
                    StandardScaffold(
                        showBottomBar = shouldShowBottomBar(navBackStackEntry),
                        modifier = Modifier.fillMaxSize(),
                        bottomNavItems = bottomNavItems,
                        selected = navController.currentDestination?.route,
                        onNewRecipeClick = {
                            navController.navigateToCreateRecipe(
                                navOptions {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            )
                        },
                        onScanClick = {
                            navController.navigateToCameraGraph(
                                navOptions {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            )
                        },
                        onBottomItemClick = {
                            if (navController.currentDestination?.route != it) {
                                navController.navigate(it) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(homeRoute) {
                                        saveState = true
                                    }
                                }
                            }
                        },
                    ) {
                        Navigation(navController)
                    }
                }
            }
        }
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        val doesRouteMatch = backStackEntry?.destination?.route in listOf(
            homeRoute,
            notificationRoute,
            AccountGraph.Home.route
        ) || backStackEntry?.destination?.route?.startsWith(searchRoute) == true
        val isOwnProfile =
            backStackEntry?.destination?.route == "${AccountGraph.ROUTE}?userId={userId}" &&
                    backStackEntry.arguments?.getString("userId") == null
        return doesRouteMatch || isOwnProfile
    }
}
