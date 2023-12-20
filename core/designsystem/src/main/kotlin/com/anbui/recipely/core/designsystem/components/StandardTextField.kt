package com.anbui.recipely.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.anbui.recipely.core.designsystem.R
import com.anbui.recipely.core.designsystem.theme.IconSizeMedium
import com.anbui.recipely.core.designsystem.theme.MediumGrey

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    suffix: String = "",
    maxLength: Int = 40,
    error: String = "",
    style: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.primary
    ),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    leadingIcon: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIconModifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            enabled = isEnabled,
            maxLines = maxLines,
            minLines = minLines,
            textStyle = style,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MediumGrey
                    ),
                    maxLines = 1

                )
            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            keyboardActions = keyboardActions,
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            singleLine = singleLine,
            leadingIcon = leadingIcon?.let {
                @Composable {
                    Icon(
                        painter = it,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = leadingIconModifier
                            .size(IconSizeMedium)
                    )
                }
            },
            trailingIcon = trailingIcon ?: {
                if (isPasswordToggleDisplayed) {
                    IconButton(
                        onClick = {
                            onPasswordToggleClick(!isPasswordVisible)
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = if (isPasswordVisible) {
                                painterResource(id = R.drawable.ic_hide)
                            } else {
                                painterResource(id = R.drawable.ic_show)
                            },
                            tint = MediumGrey,
                            contentDescription = if (isPasswordVisible) {
                                stringResource(R.string.show_password)
                            } else {
                                stringResource(R.string.hide_password)
                            }
                        )
                    }
                }
            },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.surface
            ),
            suffix = {
                Text(
                    text = suffix
                )
            }
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
