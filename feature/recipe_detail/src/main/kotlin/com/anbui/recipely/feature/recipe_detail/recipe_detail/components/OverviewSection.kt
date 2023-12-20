package com.anbui.recipely.feature.recipe_detail.recipe_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.feature.recipe_detail.R
import kotlin.math.roundToInt

fun LazyListScope.overviewSection(
    recipe: Recipe
) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NutritionItem(
                icon = R.drawable.ic_carb,
                text = stringResource(R.string.carbs, recipe.totalCarb.roundToInt()),
                modifier = Modifier.weight(1f)
            )
            NutritionItem(
                icon = R.drawable.ic_protein,
                text = stringResource(
                    R.string.proteins,
                    recipe.totalProtein.roundToInt()
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NutritionItem(
                icon = R.drawable.ic_calories,
                text = stringResource(R.string.kcal, recipe.totalCalories.roundToInt()),
                modifier = Modifier.weight(1f)

            )
            NutritionItem(
                icon = R.drawable.ic_fat,
                text = stringResource(R.string.fats, recipe.totalFat.roundToInt()),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
    }
}
