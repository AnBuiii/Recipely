package com.anbui.recipely.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.theme.IconSizeMedium

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    maxLength: Int = 40,
    error: String = "",
    style: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.onBackground
    ),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit
) {
    StandardTextField(
        modifier = modifier,
        text = text,
        hint = hint,
        maxLength = maxLength,
        error = error,
        style = style,
        singleLine = singleLine,
        maxLines = maxLines,
        leadingIcon = rememberVectorPainter(image = leadingIcon),
        keyboardType = keyboardType,
        isPasswordToggleDisplayed = isPasswordToggleDisplayed,
        isPasswordVisible = isPasswordVisible,
        onPasswordToggleClick = onPasswordToggleClick,
        onValueChange = onValueChange
    )
}

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    maxLength: Int = 40,
    error: String = "",
    style: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.onBackground
    ),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            textStyle = style,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }
                icon
            } else null,
            trailingIcon = if (isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
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
                            tint = Color.White,
                            contentDescription = if (isPasswordVisible) {
                                stringResource(R.string.show_password)
                            } else {
                                stringResource(R.string.hide_password)
                            }
                        )
                    }
                }
                icon
            } else null,
            modifier = Modifier
                .fillMaxWidth()
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
