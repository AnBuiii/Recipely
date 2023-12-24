package com.anbui.recipely.core.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.BottomNavItem
import com.anbui.recipely.core.designsystem.R

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
    var isMenuExtended by remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                StandardBottomNavigation(
                    onNewRecipeClick = {
                        onNewRecipeClick()
                        isMenuExtended = false
                    },
                    onScanClick = {
                        onScanClick()
                        isMenuExtended = false
                    },
                    isMenuExtended = isMenuExtended,
                    onToggledMenu = {
                        isMenuExtended = !isMenuExtended
                    }
                ) {
                    bottomNavItems.forEach { bottomNavItem ->
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
                                isMenuExtended = false
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
            AnimatedVisibility(visible = isMenuExtended, enter = fadeIn(), exit = fadeOut()) {
                Scrim(
                    modifier = Modifier.fillMaxSize(),
                    onClose = { isMenuExtended = false })
            }
        }
    }
}

@Composable
fun Scrim(onClose: () -> Unit, modifier: Modifier = Modifier) {
    val strClose = stringResource(R.string.close)
    Box(
        modifier
            .pointerInput(onClose) { detectTapGestures { onClose() } }
            .semantics(mergeDescendants = true) {
                contentDescription = strClose
                onClick {
                    onClose()
                    true
                }
            }
            .onKeyEvent {
                if (it.key == Key.Escape) {
                    onClose()
                    true
                } else {
                    false
                }
            }
            .background(Color.DarkGray.copy(alpha = 0.75f))
    )
}
