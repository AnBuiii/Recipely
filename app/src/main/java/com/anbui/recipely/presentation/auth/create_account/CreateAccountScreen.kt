package com.anbui.recipely.presentation.auth.create_account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.components.StandardTextField
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun CreateAccountScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isPasswordVisible by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = stringResource(R.string.create_account),
            showBackArrow = true
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
        ) {
            Text(
                text = stringResource(R.string.username),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = username,
                onValueChange = { username = it },
                hint = stringResource(R.string.username_hint),
                leadingIcon = painterResource(id = R.drawable.ic_profile)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceLarge)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.first_name),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(SpaceMedium))

                    StandardTextField(
                        text = firstName,
                        onValueChange = { firstName = it },
                        hint = stringResource(R.string.first_name_hint),
                        leadingIcon = painterResource(id = R.drawable.ic_profile)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.last_name),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(SpaceMedium))

                    StandardTextField(
                        text = lastName,
                        onValueChange = { lastName = it },
                        hint = stringResource(R.string.last_name_hint),
                        leadingIcon = painterResource(id = R.drawable.ic_message)
                    )
                }
            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.email_address),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = email,
                onValueChange = { email = it },
                hint = stringResource(R.string.email_hint),
                leadingIcon = painterResource(id = R.drawable.ic_message)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.password),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = password,
                onValueChange = { password = it },
                hint = stringResource(
                    R.string.password_hint
                ),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                keyboardType = KeyboardType.Password,
                isPasswordVisible = isPasswordVisible,
                onPasswordToggleClick = {
                    isPasswordVisible = it
                }
            )

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Button(
                onClick = {
                    navController.navigate(Screen.SelectInterestScreen.route)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = stringResource(R.string.s_continue),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }

            Spacer(modifier = Modifier.height(SpaceLarge))

            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = DarkGrey,
                    textAlign = TextAlign.Center
                ),
                text = buildAnnotatedString {
                    append(stringResource(R.string.create_account_hint))
                    append(" ")
                    withStyle(
                        SpanStyle(

                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(R.string.term_of_services))
                    }
                    append(" & ")
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(stringResource(R.string.privacy_policy))
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = SpaceLarge + SpaceMedium)
            )
        }
    }
}
