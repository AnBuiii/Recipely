package com.anbui.recipely.feature.account.edit_profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardDatePickerDialog
import com.anbui.recipely.core.designsystem.components.StandardTextField
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.ThinGrey
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.core.model.GenderType
import com.anbui.recipely.feature.account.R
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun EditProfileRoute(
    onBack: () -> Unit,
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {
    EditProfileScreen(onBack = onBack, viewModel = editProfileViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onBack: () -> Unit,
    viewModel: EditProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let(viewModel::onChangePicture)
        }
    )
    StandardDatePickerDialog(
        isOpen = uiState.openDialog,
        onChangeOpenDialog = viewModel::setOpenDialog,
        onConfirm = viewModel::onChangeDOB
    )

    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            onBack()
        }
    }

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
            title = stringResource(R.string.edit_profile),
            showBackArrow = true,
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpaceLarge)
        ) {
            AsyncImage(
                model = uiState.avatar,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
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
                        text = uiState.firstName,
                        onValueChange = viewModel::onChangeFirstName,
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
                        text = uiState.lastName,
                        onValueChange = viewModel::onChangeLastName,
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
                text = uiState.email,
                onValueChange = { },
                hint = stringResource(R.string.email_hint),
                leadingIcon = painterResource(id = R.drawable.ic_message),
                isEnabled = false
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(R.string.date_of_birth),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            StandardTextField(
                text = simpleDateFormat.format(uiState.dob),
                onValueChange = { },
                leadingIcon = painterResource(id = R.drawable.ic_birth),
                trailingIcon = {
                    IconButton(onClick = { viewModel.setOpenDialog(true) }) {
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
                text = uiState.bio,
                onValueChange = viewModel::onChangeBio,
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
                        if (uiState.gender == GenderType.Male) 2.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        if (uiState.gender != GenderType.Male) {
                            viewModel.onChangeGender(GenderType.Male)
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
                        if (uiState.gender == GenderType.Male) {
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
                        if (uiState.gender == GenderType.Female) 2.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        if (uiState.gender != GenderType.Female) {
                            viewModel.onChangeGender(GenderType.Female)
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
                        if (uiState.gender == GenderType.Female) {
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
                onClick = viewModel::onUpdateProfile,
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
