package com.anbui.recipely.presentation.main_screen.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.exampleNotifications
import com.anbui.recipely.presentation.components.RecipelyNotificationCard
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavController
) {
    val groupedNotification = exampleNotifications.groupBy { it.timeGroup }
    Column {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.notifications)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            groupedNotification.forEach { (time, notification) ->
                stickyHeader {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .background(TrueWhite)
                            .fillMaxSize()
                            .padding(horizontal = SpaceLarge)
                    )
                }
                items(notification, key = { it.id }) {
                    RecipelyNotificationCard(
                        notification = it,
                        modifier = Modifier.padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceSmall + SpaceTiny
                        )
                    )
                }
            }


        }
    }


}