package com.anbui.recipely.presentation.camera

import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.ui.theme.Dark
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite


@ExperimentalMaterial3Api
@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current

//    val permissionState = rememberMultiplePermissionsState(
//        permissions = listOf(
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO
//        )
//    )

    val previewView: PreviewView = remember { PreviewView(context) }

    LaunchedEffect(Unit) {
        viewModel.setUpCamera(context, previewView, lifecycleOwner, configuration.orientation)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.small,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = TrueWhite
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "")
            }
            Text(text = "Scannings...", style = MaterialTheme.typography.headlineSmall)
            FilledIconButton(
                onClick = { /*TODO*/ }, shape = MaterialTheme.shapes.small,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = TrueWhite,
                    contentColor = Dark
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_image),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
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
                onClick = { /*TODO*/ }, shape = MaterialTheme.shapes.medium,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = TrueWhite
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "")
            }
            Text(text = "Scanning", style = MaterialTheme.typography.headlineSmall)
            FilledIconButton(
                onClick = { /*TODO*/ }, shape = MaterialTheme.shapes.medium,
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
        ){
            items(viewModel.resultList){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TrueWhite)
                        .padding(SpaceSmall)
                ) {
                    Text(text = it.label)
                    Text(text = it.score.toString())
                }
            }
        }
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
////                .height(300.dp)
//        ) {
//            viewModel.resultList.forEach {
//
//            }
//        }


    }
}

