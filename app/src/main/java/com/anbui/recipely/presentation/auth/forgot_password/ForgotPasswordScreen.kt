package com.anbui.recipely.presentation.auth.forgot_password

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardTextField
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.forgot_password),
            showBackArrow = true
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
        ) {

            Text(
                text = stringResource(R.string.forgot_password_description),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = DarkGrey,
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.height(SpaceLarge))
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



            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Button(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = stringResource(R.string.send_email),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }

        }


    }
}