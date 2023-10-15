package com.anbui.recipely.presentation.other_feature.edit_profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.GenderType
import com.anbui.recipely.domain.models.exampleAccounts
import com.anbui.recipely.presentation.ui.components.StandardDatePickerDialog
import com.anbui.recipely.presentation.ui.components.StandardTextField
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.ThinGrey
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalStdlibApi::class)
@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    navController: NavController
) {
    val account = exampleAccounts[0]
    var email by remember { mutableStateOf(account.email) }
    var firstName by remember { mutableStateOf(account.firstName) }
    var lastName by remember { mutableStateOf(account.lastName) }
    var bio by remember { mutableStateOf(account.bio) }
    var dob by remember {
        mutableStateOf(account.dob)
    }
    var gender by remember {
        mutableStateOf(account.gender)
    }
    var openDialog by remember { mutableStateOf(false) }

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    StandardDatePickerDialog(
        isOpen = openDialog,
        onChangeOpenDialog = { openDialog = it },
        onConfirm = {
            dob = it
        }
    )

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        StandardToolbar(
            navController = navController,
            title = stringResource(R.string.edit_profile),
            showBackArrow = true
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceLarge)
        ) {
            AsyncImage(
                model = account.avatarUrl,
                contentDescription = account.id,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .clickable { }
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
                text = stringResource(R.string.date_of_birth),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = simpleDateFormat.format(dob),
                onValueChange = { email = it },
                leadingIcon = painterResource(id = R.drawable.ic_birth),
                trailingIcon = {
                    IconButton(onClick = { openDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_down),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                isEnabled = false
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.bio),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = bio,
                onValueChange = { bio = it },
                hint = stringResource(R.string.recipely_lover),
                leadingIcon = painterResource(id = R.drawable.ic_edit),
                minLines = 2,
                maxLines = 2,
                maxLength = 60,
                singleLine = false,
                leadingIconModifier = Modifier.offset(y = (-12).dp)

            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.gender),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Row(
                modifier = Modifier.selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(SpaceSmall)
            ) {
                Card(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .shadow(
                            elevation = 16.dp,
                            spotColor = ThinGrey,
                            ambientColor = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.large
                        )
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = TrueWhite),
                    border = BorderStroke(
                        if (gender == GenderType.Male) 2.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        if (gender != GenderType.Male) {
                            gender = GenderType.Male
                        }
                    }

                ) {
                    Box(
                        modifier = Modifier
                            .padding(SpaceMedium)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Male",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (gender == GenderType.Male) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_tick),
                                contentDescription = stringResource(id = R.string.tick),
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                }

                Card(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .shadow(
                            elevation = 16.dp,
                            spotColor = ThinGrey,
                            ambientColor = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.large
                        )
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = TrueWhite),
                    border = BorderStroke(
                        if (gender == GenderType.Female) 2.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        if (gender != GenderType.Female) gender = GenderType.Female
                    }

                ) {
                    Box(
                        modifier = Modifier
                            .padding(SpaceMedium)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Male",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (gender == GenderType.Female) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_tick),
                                contentDescription = stringResource(id = R.string.tick),
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(SpaceLarge))

            Button(
                onClick = {
                    navController.popBackStack()
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )

            ) {
                Text(
                    text = stringResource(R.string.update_profile),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                    modifier = Modifier.padding(vertical = SpaceSmall)
                )
            }

            Spacer(modifier = Modifier.height(SpaceLarge))
        }
    }
}