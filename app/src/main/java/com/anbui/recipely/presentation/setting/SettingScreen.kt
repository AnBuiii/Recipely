package com.anbui.recipely.presentation.setting

import android.app.LocaleManager
import android.os.LocaleList
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.setting.components.SettingCard
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    var notification by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Column {
        StandardToolbar(
            navController = navController,
            title = stringResource(R.string.settings),
            showBackArrow = true
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceLarge),
            verticalArrangement = Arrangement.spacedBy(SpaceLarge)
        ) {
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_notification),
                text = stringResource(
                    id = R.string.notifications
                ),
                trailing = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (notification) stringResource(R.string.on) else stringResource(
                                R.string.off
                            ),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = MediumGrey
                            )
                        )
                        Switch(
                            checked = notification,
                            onCheckedChange = {
                                notification = it

                                context.getSystemService(
                                    LocaleManager::class.java
                                ).applicationLocales =
                                    LocaleList(Locale.forLanguageTag(if (it) "vi-VN" else "en-US"))



                                Log.d("Change", it.toString())
                            },
                            colors = SwitchDefaults.colors(
                                uncheckedBorderColor = MaterialTheme.colorScheme.primary,
                                uncheckedIconColor = MaterialTheme.colorScheme.primary,
                                uncheckedThumbColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                },
                onClick = { notification = !notification }
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_time_square),
                text = stringResource(R.string.your_orders),
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_language),
                text = stringResource(R.string.language),
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_info),
                text = stringResource(R.string.about_app)
            )

        }
    }
}
