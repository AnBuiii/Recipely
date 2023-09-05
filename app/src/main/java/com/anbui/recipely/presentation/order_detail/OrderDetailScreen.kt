package com.anbui.recipely.presentation.order_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.components.Stepper

@ExperimentalMaterial3Api
@Composable
fun OrderDetailScreen(
    navController: NavController
) {
    Column {
        StandardToolbar(
            navController = navController,
            title = "Order #3123123",
            showBackArrow = true
        )
        val numberStep = 4
        var currentStep by rememberSaveable { mutableStateOf(3) }
        val titleList= arrayListOf("Step 1","Step 2","Step 3","Step 4")

        Stepper(
            numberOfSteps = numberStep,
            currentStep = currentStep,
            stepDescriptionList = titleList
        )
    }
}