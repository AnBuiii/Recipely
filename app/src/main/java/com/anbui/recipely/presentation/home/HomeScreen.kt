package com.anbui.recipely.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.anbui.recipely.presentation.home.components.FeaturedSection
import com.anbui.recipely.presentation.home.components.HeadingSection
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge

@Composable
fun HomeScreen(
    navController: NavController
) {
    val name = "An Bùi"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SpaceHuge)
    ) {

        HeadingSection(
            name = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge)
        )
        
        Spacer(modifier = Modifier.height(SpaceLarge))

        FeaturedSection(modifier = Modifier)
    }


}