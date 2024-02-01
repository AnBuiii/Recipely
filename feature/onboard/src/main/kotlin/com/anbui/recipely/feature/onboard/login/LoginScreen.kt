package com.anbui.recipely.feature.onboard.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.StandardTextField
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.GoogleRed
import com.anbui.recipely.core.designsystem.theme.MediumGrey
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.onboard.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginRoute(
    onNavigateToHome: () -> Unit,
    onBack: () -> Unit,
    onForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        onNavigateToHome = onNavigateToHome,
        loginViewModel = loginViewModel,
        onBack = onBack,
        onForgotPassword = onForgotPassword
    )
}

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by loginViewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(uiState.state) {
        when (uiState.state) {
            State.Init -> {}
            State.Fail -> {
                coroutineScope.launch {
                    Toast.makeText(
                        context, "Email or password not match", Toast.LENGTH_SHORT
                    ).show()
                    delay(1000)
                    loginViewModel.changeState(State.Init)
                }
            }

            State.Success -> {
                onNavigateToHome()
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            modifier = Modifier.semantics {
                contentDescription = "Login"
            },
            title = stringResource(R.string.login),
            showBackArrow = true,
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
        ) {
            Text(
                text = stringResource(R.string.email_address),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                modifier = Modifier.semantics {
                    contentDescription = "emailTextField"
                },
                text = uiState.email,
                onValueChange = loginViewModel::changeEmail,
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
                modifier = Modifier.semantics {
                    contentDescription = "passwordTextField"
                },
                text = uiState.password,
                onValueChange = loginViewModel::changePassword,
                hint = stringResource(R.string.password_hint),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                keyboardType = KeyboardType.Password,
                isPasswordVisible = uiState.passwordVisible,
                onPasswordToggleClick = loginViewModel::changePasswordVisibility
            )

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Button(
                onClick = loginViewModel::login,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "loginButton"
                    }

            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            TextButton(
                onClick = onForgotPassword,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password) + "?",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                stringResource(R.string.or_continue_with),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = MediumGrey
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = onNavigateToHome,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoogleRed
                )

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_google),
                        contentDescription = stringResource(R.string.google)
                    )
                    Text(
                        text = stringResource(R.string.login_with_google),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = SpaceSmall)
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
        }
    }
}

