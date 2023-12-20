package com.anbui.recipely.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.BottomNavItem

@ExperimentalMaterial3Api
@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    bottomNavItems: List<BottomNavItem>,
    onNewRecipeClick: () -> Unit,
    onScanClick: () -> Unit,
    onBottomItemClick: (String) -> Unit,
    selected: String?,
    content: @Composable () -> Unit,
) {

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                StandardBottomNavigation(
                    onNewRecipeClick = onNewRecipeClick,
                    onScanClick = onScanClick
                ) {
                    bottomNavItems.forEachIndexed { i, bottomNavItem ->
                        if (bottomNavItem.unselectedIcon != null) {
                            StandardBottomNavItem(
                                contentDescription = bottomNavItem.contentDescription,
                                unselectedPainter = painterResource(
                                    id = bottomNavItem.unselectedIcon
                                ),
                                selectedPainter = painterResource(id = bottomNavItem.selectedIcon),
                                selected = selected?.startsWith(
                                    bottomNavItem.route.trim()
                                ) == true,
                                alertCount = bottomNavItem.alertCount,
                                enabled = true
                            ) {
                                onBottomItemClick(bottomNavItem.route)
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
        Box {
            content()
        }
    }
}
