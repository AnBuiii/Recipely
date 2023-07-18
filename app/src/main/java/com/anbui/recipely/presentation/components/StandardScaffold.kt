package com.anbui.recipely.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.BottomNavItem
import com.anbui.recipely.domain.models.BottomNavItem.*
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
//    state: ScaffoldState,

    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.HomeScreen.route,
            icon = R.drawable.ic_home,
            contentDescription = stringResource(R.string.home)
        ),

        BottomNavItem(
            route = Screen.SearchScreen.route,
            icon = R.drawable.ic_search,
            contentDescription = stringResource(R.string.search)
        ),
        BottomNavItem(
            route = Screen.NotificationScreen.route,
            icon = R.drawable.ic_notification,
            contentDescription = stringResource(R.string.notification)
        ),
        BottomNavItem(
            route = Screen.AccountScreen.route,
            icon = R.drawable.ic_profile,
            contentDescription = stringResource(R.string.account)
        ),
    ),
    onFabClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp
                ) {
                    bottomNavItems.forEachIndexed { i, bottomNavItem ->
                        StandardBottomNavItem(
                            painter = painterResource(id = bottomNavItem.icon),
                            contentDescription = bottomNavItem.contentDescription,
                            selected = navController.currentDestination?.route?.startsWith(
                                bottomNavItem.route
                            ) == true,
                            alertCount = bottomNavItem.alertCount,
                            enabled = true,
                            onClick = {
                                if (navController.currentDestination?.route != bottomNavItem.route) {
                                    navController.navigate(bottomNavItem.route)
                                }
                            },
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = onFabClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.make_recipe)
                    )
                }
            }
        },
//        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
        Box(

        ) {
            content(it)
        }

    }
}