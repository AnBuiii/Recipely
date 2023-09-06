package com.anbui.recipely.presentation.order_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anbui.recipely.presentation.components.StandardCard
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.components.Stepper
import com.anbui.recipely.presentation.ui.theme.SpaceLarge

@ExperimentalMaterial3Api
@Composable
fun OrderDetailScreen(
    navController: NavController
) {
    Column {
        StandardToolbar(
            navController = navController,
            title = "Order",
            showBackArrow = true
        )
        val numberStep = 4
        var currentStep by rememberSaveable { mutableStateOf(1) }
        val titleList = arrayListOf("Step 1", "Step 2", "Step 3", "Step 4")

        StandardCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge),
        ) {
            Text(text = "Order ID: 123321")
            Text(text = "Order date: 20:05, Yesterday")
            Text(text = "Success delivery")
        }
        StandardCard(
            modifier = Modifier
                .fillMaxWidth()
//                .height(300.dp)
                .padding(horizontal = SpaceLarge),
        ) {
            Text(text = "Order status")
            Spacer(modifier = Modifier.height(SpaceLarge))
            Stepper(
                numberOfSteps = numberStep,
                currentStep = currentStep,
                stepDescriptionList = titleList,
            )
        }


    }
}