package com.anbui.recipely.feature.account.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.RecipelyAccountCard
import com.anbui.recipely.core.designsystem.components.RecipelyTinyVerticallyCard
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.feature.account.R
import com.anbui.recipely.feature.account.account.components.OrderItem

@Composable
fun AccountRoute(
    onBack: () -> Unit,
    onNavigateToSettingScreen: () -> Unit,
    onNavigateToEditProfileScreen: () -> Unit,
    onNavigateToOrderDetail: (String) -> Unit,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    AccountScreen(
        onBack = onBack,
        onNavigateToSettingScreen = onNavigateToSettingScreen,
        onNavigateToEditProfileScreen = onNavigateToEditProfileScreen,
        onNavigateToOrderDetail = onNavigateToOrderDetail,
        viewModel = accountViewModel
    )
}

@Composable
fun AccountScreen(
    onBack: () -> Unit,
    onNavigateToSettingScreen: () -> Unit,
    onNavigateToEditProfileScreen: () -> Unit,
    onNavigateToOrderDetail: (String) -> Unit,
    viewModel: AccountViewModel
) {
    val currentAccount by viewModel.currentAccount.collectAsStateWithLifecycle()
    val favouriteRecipes by viewModel.favouriteRecipes.collectAsStateWithLifecycle()
    val comingOrder by viewModel.comingOrder.collectAsStateWithLifecycle()
    Column {
        StandardToolbar(
            title = stringResource(id = R.string.account),
            navActions = {
                IconButton(
                    onClick = onNavigateToSettingScreen,
                    modifier = Modifier.padding(end = SpaceSmall)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = stringResource(
                            id = R.string.settings
                        )
                    )
                }
            },
            onBack = onBack
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(SpaceMedium),
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
            contentPadding = PaddingValues(SpaceLarge)
        ) {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                RecipelyAccountCard(
                    account = currentAccount,
                    onClick = onNavigateToEditProfileScreen
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.incoming_order),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.see_all),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.padding(start = SpaceLarge)
                    )
                }
            }
            if (comingOrder.isNotEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    OrderItem(comingOrder[0], onClick = {
                        onNavigateToOrderDetail(comingOrder[0].id)
                    })
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.my_favourite),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.see_all),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.padding(start = SpaceLarge)
                    )
                }
            }
            items(favouriteRecipes, key = { "popular ${it.id}" }) {
                RecipelyTinyVerticallyCard(
                    recipe = it
                )
            }
        }
    }
}
