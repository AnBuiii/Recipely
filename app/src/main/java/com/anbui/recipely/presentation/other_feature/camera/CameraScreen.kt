package com.anbui.recipely.presentation.other_feature.camera

import android.Manifest
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.Screen
import com.anbui.recipely.util.permission.ExperimentalPermissionsApi
import com.anbui.recipely.util.permission.isGranted
import com.anbui.recipely.util.permission.rememberPermissionState
import com.anbui.recipely.util.permission.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalMaterial3Api
@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current


    val previewView: PreviewView = remember { PreviewView(context) }
    val result by viewModel.result.collectAsStateWithLifecycle()

    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA,
    ) { granted ->
        if (granted) {
            viewModel.setUpCamera(context, previewView, lifecycleOwner, configuration.orientation)
        } else {
            navController.popBackStack()
        }
    }
    LaunchedEffect(Unit) {
        if(cameraPermissionState.status.shouldShowRationale){
            navController.popBackStack()
        }
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        } else {
            viewModel.setUpCamera(context, previewView, lifecycleOwner, configuration.orientation)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpaceHuge, start = SpaceMedium, end = SpaceMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilledIconButton(
                onClick = {
                    navController.popBackStack()
                },
                shape = MaterialTheme.shapes.medium,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = TrueWhite
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "")
            }
            Text(text = "Scanning", style = MaterialTheme.typography.headlineSmall)
            FilledIconButton(
                onClick = viewModel::changeScan,
                shape = MaterialTheme.shapes.medium,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = TrueWhite
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_image),
                    contentDescription = ""
                )
            }
        }

        LazyColumn(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            items(
                result.filterNotNull(),
                key = {
                    it.label
                }
            ) {
                val displayName = it.label.replaceFirstChar { c ->
                    c.uppercase()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TrueWhite)
                        .clickable {
                            navController.navigate(
                                "${Screen.SearchScreen.route}/" + displayName
                            ) {
                                popUpTo(Screen.CameraScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                        .padding(SpaceSmall)
                ) {
                    Text(text = displayName) // name
                }
            }
        }
    }
}
