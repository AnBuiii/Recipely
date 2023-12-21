package com.anbui.recipely.feature.account.setting

import android.app.LocaleManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.MediumGrey
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.account.R
import com.anbui.recipely.feature.account.setting.components.LanguagePickerBottomSheet
import com.anbui.recipely.feature.account.setting.components.SettingCard
import kotlinx.coroutines.launch

@Composable
fun SettingRoute(
    onBack: () -> Unit,
    navigateToMyOrder: () -> Unit,
    navigateToMyRecipe: () -> Unit,
    navigateToOnboard: () -> Unit,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    SettingScreen(
        onBack = onBack,
        navigateToMyOrder = navigateToMyOrder,
        navigateToMyRecipe = navigateToMyRecipe,
        navigateToOnboard = navigateToOnboard,
        settingViewModel = settingViewModel
    )
}

@Composable
fun SettingScreen(
    onBack: () -> Unit,
    navigateToMyOrder: () -> Unit,
    navigateToMyRecipe: () -> Unit,
    navigateToOnboard: () -> Unit,
    settingViewModel: SettingViewModel
) {
    var notification by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val language =
        context.getSystemService(LocaleManager::class.java).applicationLocales.toLanguageTags()
    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()

    LanguagePickerBottomSheet(isBottomSheetOpen) { isBottomSheetOpen = it }
    Column {
        StandardToolbar(
            title = stringResource(R.string.settings),
            showBackArrow = true,
            onBack = onBack
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
                            text = if (notification) {
                                stringResource(androidx.compose.ui.R.string.on)
                            } else {
                                stringResource(
                                    androidx.compose.ui.R.string.off
                                )
                            },
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = MediumGrey
                            )
                        )
                        Switch(
                            checked = notification,
                            onCheckedChange = {
//                                notification = it
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
                leadingIcon = painterResource(id = R.drawable.ic_cook),
                text = stringResource(R.string.my_recipe),
                onClick = navigateToMyRecipe
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_time_square),
                text = stringResource(R.string.my_orders),
                onClick = navigateToMyOrder
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_language),
                text = stringResource(R.string.language),
                trailing = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (language == "en-US") {
                                stringResource(
                                    R.string.current_language
                                )
                            } else {
                                "Tiếng Việt"
                            },
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = MediumGrey
                            )
                        )
                        FilledIconButton(
                            onClick = {},
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = TrueWhite
                            ),
                            modifier = Modifier
                                .padding(end = SpaceTiny)
                                .size(32.dp),
                            shape = MaterialTheme.shapes.medium

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_right),
                                contentDescription = stringResource(
                                    R.string.arrow_right
                                )
                            )
                        }
                    }
                },
                onClick = {
                    isBottomSheetOpen = true
                }
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_info),
                text = stringResource(R.string.about_app)
            )
            SettingCard(
                leadingIcon = painterResource(id = R.drawable.ic_info),
                text = stringResource(R.string.log_out),
                onClick = {
                    coroutineScope.launch {
                        settingViewModel.logout()
                        navigateToOnboard()
                    }
                }
            )
        }
    }
}
