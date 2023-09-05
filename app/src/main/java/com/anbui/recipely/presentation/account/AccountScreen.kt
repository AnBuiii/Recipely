package com.anbui.recipely.presentation.account

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.exampleAccounts
import com.anbui.recipely.domain.models.exampleOrder
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.account.components.OrderItem
import com.anbui.recipely.presentation.components.RecipelyAccountCard
import com.anbui.recipely.presentation.components.RecipelyTinyVerticallyCard
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    navController: NavController
) {
    Column {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.account),
            navActions = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.SettingScreen.route) {
                            launchSingleTop = true
                        }

                    },
                    modifier= Modifier.padding(end = SpaceSmall)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = stringResource(
                            id = R.string.settings
                        )
                    )
                }

            }
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(SpaceMedium),
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
            contentPadding = PaddingValues(SpaceLarge)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                RecipelyAccountCard(
                    account = exampleAccounts[0],
                    onClick = { navController.navigate(Screen.EditProfileScreen.route) }
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.incoming_order),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = stringResource(R.string.see_all),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.padding(start = SpaceLarge)
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                OrderItem(
                    exampleOrder[0],
                    onClick = {navController.navigate(Screen.OrderDetailScreen.route)}
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.my_favourite),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = stringResource(R.string.see_all),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.padding(start = SpaceLarge)
                    )
                }
            }

            items(exampleRecipes, key = { "popular ${it.id}" }) {
                RecipelyTinyVerticallyCard(
                    recipe = it,
                )
            }
        }

    }

}