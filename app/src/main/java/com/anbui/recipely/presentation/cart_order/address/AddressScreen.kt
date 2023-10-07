package com.anbui.recipely.presentation.cart_order.address

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.components.StandardTextField
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@ExperimentalMaterial3Api
@Composable
fun AddressScreen(
    navController: NavController,
    addressScreenViewModel: AddressScreenViewModel = hiltViewModel()
) {
    var street by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }

    Column {
        StandardToolbar(navController = navController, title = "Your address", showBackArrow = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceLarge, vertical = SpaceMedium)
        ) {
            Text(text = stringResource(R.string.street), style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(text = street, onValueChange = {
                street = it
            }, hint = stringResource(R.string.street_hint), leadingIcon = painterResource(id = R.drawable.ic_message))

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(text = stringResource(R.string.district), style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(text = district, onValueChange = {
                district = it
            }, hint = stringResource(R.string.district_hint), leadingIcon = painterResource(id = R.drawable.ic_lock))

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Text(text = stringResource(R.string.province_city), style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = district,
                onValueChange = {
                    district = it
                },
                hint = stringResource(
                    R.string.province_city_hint
                ),
                leadingIcon = painterResource(id = R.drawable.ic_lock)
            )

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }
        }
    }
}
