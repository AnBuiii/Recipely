package com.anbui.recipely.feature.cart.address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anbui.recipely.core.designsystem.components.StandardTextField
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.cart.R
import com.anbui.recipely.feature.cart.CartViewModel

@Composable
fun AddressRoute(
    onBack: () -> Unit,
    cartViewModel: CartViewModel
) {
    AddressScreen(onBack = onBack, viewModel = cartViewModel)
}

@Composable
fun AddressScreen(
    onBack: () -> Unit,
    viewModel: CartViewModel
) {
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.addressState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.changeSuccess) {
        if (uiState.changeSuccess == true) {
            // todo do something
        }
    }
    Column {
        StandardToolbar(
            title = "Your address",
            showBackArrow = true,
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceLarge, vertical = SpaceMedium)
        ) {
            Text(
                text = stringResource(R.string.street),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.street,
                onValueChange = viewModel::changeStreet,
                hint = stringResource(R.string.street_hint),
                leadingIcon = painterResource(id = R.drawable.ic_message)
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.district),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.district,
                onValueChange = viewModel::changeDistrict,
                hint = stringResource(R.string.district_hint),
                leadingIcon = painterResource(id = R.drawable.ic_lock)
            )

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Text(
                text = stringResource(R.string.province_city),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = uiState.province,
                onValueChange = viewModel::changeProvince,
                hint = stringResource(
                    R.string.province_city_hint
                ),
                leadingIcon = painterResource(id = R.drawable.ic_lock)
            )

            Spacer(modifier = Modifier.height(SpaceLarge + SpaceSmall))

            Button(
                onClick = {
                    viewModel.updateAddress()
                    focusManager.clearFocus()
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
