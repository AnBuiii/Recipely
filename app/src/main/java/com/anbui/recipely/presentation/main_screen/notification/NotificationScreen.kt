package com.anbui.recipely.presentation.main_screen.notification

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
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.components.RecipelyNotificationCard
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import java.text.ParseException
import java.time.LocalDateTime
import java.time.ZoneId


fun LocalDateTime.timeAgo(minResolution: Long): String {
    return try {
        val then = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val now = System.currentTimeMillis()
        val ago = DateUtils.getRelativeTimeSpanString(
            then,
            now,
            minResolution,
        )
        ago.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
        "fail"
    }
}

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavController,
    notificationScreenViewModel: NotificationScreenViewModel = hiltViewModel()
) {
    val notification by notificationScreenViewModel.notifications.collectAsStateWithLifecycle()

    val groupedNotification by remember(notification) {
        derivedStateOf {
            notification.groupBy {
                it.time.timeAgo(DateUtils.DAY_IN_MILLIS)
            }
        }
    }

    Column {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.notifications)
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
                            notificationScreenViewModel.readNotification(it.id)
                        }
                    )
                }
            }
        }
    }
}
