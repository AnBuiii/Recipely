package com.example.ingredient_detect

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
import com.anbui.recipely.core.designsystem.theme.SpaceHuge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.ingredient_detect.R
import com.example.ingredient_detect.permission.isGranted
import com.example.ingredient_detect.permission.rememberPermissionState
import com.example.ingredient_detect.permission.shouldShowRationale

@Composable
fun CameraRoute(
    onBack: () -> Unit,
    navigateToSearch: (String) -> Unit,
    cameraViewModel: CameraViewModel = hiltViewModel()
) {
    CameraScreen(onBack = onBack, navigateToSearch = navigateToSearch, viewModel = cameraViewModel)
}

@Composable
fun CameraScreen(
    onBack: () -> Unit,
    navigateToSearch: (String) -> Unit,
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
            onBack()
        }
    }
    LaunchedEffect(Unit) {
        if (cameraPermissionState.status.shouldShowRationale) {
            onBack()
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
                onClick = onBack,
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
                            navigateToSearch(displayName)
                        }
                        .padding(SpaceSmall)
                ) {
                    Text(text = displayName) // name
                }
            }
        }
    }
}
