package com.anbui.recipely.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.BottomNavItem
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.HomeScreen.route,
            unselectedIcon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_home_filled,
            contentDescription = stringResource(R.string.home)
        ),

        BottomNavItem(
            route = Screen.SearchScreen.route,
            unselectedIcon = R.drawable.ic_search,
            selectedIcon = R.drawable.ic_search_filled,
            contentDescription = stringResource(R.string.search)
        ),
        BottomNavItem(
            route = Screen.SearchScreen.route,
            unselectedIcon = null,
            selectedIcon = R.drawable.ic_search_filled,
            contentDescription = stringResource(R.string.search)
        ),
        BottomNavItem(
            route = Screen.NotificationScreen.route,
            selectedIcon = R.drawable.ic_notification_filled,
            unselectedIcon = R.drawable.ic_notification,
            contentDescription = stringResource(R.string.notifications)
        ),
        BottomNavItem(
            route = Screen.AccountScreen.route,
            selectedIcon = R.drawable.ic_profile_filled,
            unselectedIcon = R.drawable.ic_profile,
            contentDescription = stringResource(R.string.account)
        )
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                StandardBottomNavigation(
                    onNewRecipeClick = {
                        navController.navigate(Screen.CreateRecipeScreen.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onScanClick = {
                        navController.navigate(Screen.CameraScreen.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ) {
                    bottomNavItems.forEachIndexed { i, bottomNavItem ->
                        if (bottomNavItem.unselectedIcon != null) {
                            StandardBottomNavItem(
                                contentDescription = bottomNavItem.contentDescription,
                                unselectedPainter = painterResource(
                                    id = bottomNavItem.unselectedIcon
                                ),
                                selectedPainter = painterResource(id = bottomNavItem.selectedIcon),
                                selected = navController.currentDestination?.route?.startsWith(
                                    bottomNavItem.route
                                ) == true,
                                alertCount = bottomNavItem.alertCount,
                                enabled = true
                            ) {
                                if (navController.currentDestination?.route != bottomNavItem.route) {
                                    navController.navigate(bottomNavItem.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                        popUpTo(Screen.HomeScreen.route) {
                                            saveState = true
                                        }
                                    }
                                }
                            }
                        } else {
                            Box(modifier = Modifier.width(64.dp))
                        }
                    }
                }
            }
        },
        modifier = modifier
    ) {
        val a = it
        Box() {
            content()
        }
    }
}
