package com.anbui.recipely.feature.onboard.select_interest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.onboard.Interest
import com.anbui.recipely.feature.onboard.R
import com.anbui.recipely.feature.onboard.select_interest.components.InterestCard

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun SelectInterestScreen(
    onNavigateHome: () -> Unit,
    onBack: () -> Unit
) {
    val interests = listOf(
        Interest.DiscoverRecipe,
        Interest.HealthyLiving,
        Interest.EasyFit,
        Interest.Vegetarian,
        Interest.Gluten,
        Interest.NutFree,
        Interest.EasyCooking,
        Interest.GoodFats
    )

    val selectedItem = remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            title = stringResource(R.string.select_interest),
            showBackArrow = true,
            onBack = onBack
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceLarge)
        ) {
            interests.map { interestItem ->
                InterestCard(
                    interest = interestItem,
                    isSelected = selectedItem.contains(interestItem.title),
                    onClick = { select ->
                        if (select) {
                            println("click")
                            selectedItem.add(interestItem.title)
                        } else {
                            selectedItem.remove(interestItem.title)
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNavigateHome,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = SpaceLarge, end = SpaceLarge, bottom = SpaceLarge * 2)

        ) {
            Text(
                text = stringResource(R.string.s_continue),
                style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                modifier = Modifier.padding(vertical = SpaceSmall)
            )
        }
    }
}
