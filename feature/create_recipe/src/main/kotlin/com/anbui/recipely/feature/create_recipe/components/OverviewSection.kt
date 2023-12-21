package com.anbui.recipely.feature.create_recipe.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardHorizontalPagerIndicator
import com.anbui.recipely.core.designsystem.components.StandardTextField
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.ThinGrey
import com.anbui.recipely.feature.create_recipe.CreateRecipeEvent
import com.anbui.recipely.feature.create_recipe.R

@ExperimentalFoundationApi
@Composable
fun OverviewSection(
    onEvent: (CreateRecipeEvent) -> Unit,
    selectedImages: List<Uri?>,
    title: String,
    description: String,
    servings: String
) {
    val pagerState = rememberPagerState { (selectedImages.size + 1).coerceAtMost(3) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
                pageSpacing = SpaceMedium
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colors = CardDefaults.cardColors(containerColor = ThinGrey)
                ) {
                    val photoPickerLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.PickVisualMedia(),
                        onResult = { uri ->
                            uri?.let {
                                if (selectedImages.size == page || selectedImages[page] == null) {
                                    onEvent(CreateRecipeEvent.AddImage(uri))
                                } else {
                                    onEvent(CreateRecipeEvent.EditImage(uri, page))
                                }
                            }
                        }
                    )

                    if (selectedImages.size == page || selectedImages[page] == null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(SpaceMedium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_recipely),
                                    contentDescription = stringResource(
                                        R.string.recipely
                                    ),
                                    modifier = Modifier.size(36.dp)
                                )
                                Text(text = stringResource(R.string.title_image_hint))
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(
                                model = selectedImages[page],
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                            FilledIconButton(
                                onClick = {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(SpaceMedium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_edit),
                                    contentDescription = stringResource(
                                        R.string.edit
                                    )
                                )
                            }

                            FilledIconButton(
                                onClick = {
                                    onEvent(CreateRecipeEvent.RemoveImage(page))
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(SpaceMedium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = stringResource(
                                        R.string.edit
                                    )
                                )
                            }
                        }
                    }
                }
            }
            StandardHorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = (selectedImages.size + 1).coerceAtMost(3),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(vertical = SpaceLarge + SpaceSmall)
            )
        }

        Spacer(modifier = Modifier.height(SpaceMedium))

        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        StandardTextField(
            text = title,
            onValueChange = { onEvent(CreateRecipeEvent.EnterTitle(it)) },
            hint = stringResource(R.string.enter_recipe_title)
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        StandardTextField(
            text = description,
            onValueChange = { onEvent(CreateRecipeEvent.EditDescription(it)) },
            hint = stringResource(R.string.create_recipe_description_hint),
            minLines = 3,
            maxLines = Int.MAX_VALUE,
            singleLine = false,
            maxLength = 90
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        Text(
            text = stringResource(R.string.serves),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        StandardTextField(
            text = servings,
            onValueChange = { onEvent(CreateRecipeEvent.EditServings(it)) },
            hint = "1",
            keyboardType = KeyboardType.Number,
            suffix = " people"
        )

        Spacer(modifier = Modifier.height(SpaceMedium))
    }
}
