package com.anbui.recipely.presentation.auth.login

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.components.StandardTextField
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.GoogleRed
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by loginViewModel.state.collectAsStateWithLifecycle()
    val coroutineScope  = rememberCoroutineScope()
    LaunchedEffect(uiState.error){
        uiState.error?.let {
            coroutineScope.launch {
                Log.d("LoginScreen", it)
                delay(1000)
                loginViewModel.changeError(null)
            }
        }
    }

    LaunchedEffect(uiState.success){
        if(uiState.success){
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.login),
            showBackArrow = true
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
                text = uiState.password,
                onValueChange = loginViewModel::changePassword,
                hint = stringResource(
                    R.string.password_hint
                ),
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                keyboardType = KeyboardType.Password,
                isPasswordVisible = uiState.passwordVisible,
                onPasswordToggleClick = loginViewModel::changePasswordVisibility
            )

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Button(
                onClick = loginViewModel::login,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            TextButton(
                onClick = {
                    navController.navigate(Screen.ForgotPasswordScreen.route)
                },
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
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
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
                        contentDescription = stringResource(
                            R.string.google
                        )
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
