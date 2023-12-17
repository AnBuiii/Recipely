package com.anbui.recipely.feature.notification

import android.text.format.DateUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.RecipelyNotificationCard
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.designsystem.theme.TrueWhite

@Composable
fun NotificationRoute(
    onBack: () -> Unit,
    notificationScreenViewModel: NotificationScreenViewModel = hiltViewModel()
) {
    NotificationScreen(onBack = onBack, viewModel = notificationScreenViewModel)
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotificationScreen(
    onBack: () -> Unit,
    viewModel: NotificationScreenViewModel
) {
    val notification by viewModel.notifications.collectAsStateWithLifecycle()

    val groupedNotification by remember(notification) {
        derivedStateOf {
            notification.groupBy {
                it.time.timeAgo(DateUtils.DAY_IN_MILLIS)
            }
        }
    }

    Column {
        StandardToolbar(
            onBack = onBack,
            title = stringResource(R.string.notifications)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp)
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
                        modifier = Modifier.padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceSmall + SpaceTiny
                        ),
                        notification = it,
                        onClick = {
                            viewModel.readNotification(it.id)
                        }
                    )
                }
            }
        }
    }
}
