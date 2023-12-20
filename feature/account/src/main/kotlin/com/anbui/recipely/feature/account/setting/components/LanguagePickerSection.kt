package com.anbui.recipely.feature.account.setting.components

import android.app.LocaleManager
import android.os.LocaleList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagePickerBottomSheet(
    isOpen: Boolean,
    onChangeOpenState: (Boolean) -> Unit

) {
    val bottomSheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    val language =
        context.getSystemService(LocaleManager::class.java).applicationLocales.toLanguageTags()
    if (isOpen) {
        ModalBottomSheet(
            onDismissRequest = { onChangeOpenState(false) },
            sheetState = bottomSheetState,
            windowInsets = WindowInsets(0)
        ) {
            val radioOptions = mapOf("vi-VN" to "Tiếng Việt", "en-US" to "English")
            Column(
                Modifier
                    .selectableGroup()
                    .padding(bottom = SpaceLarge)
            ) {
                radioOptions.keys
                    .forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == language),
                                    onClick = {
                                        context.getSystemService(
                                            LocaleManager::class.java
                                        ).applicationLocales =
                                            LocaleList(Locale.forLanguageTag(text))
                                    },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = SpaceMedium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == language),
                                onClick = null
                            )
                            radioOptions[text]?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
            }
        }
    }
}
