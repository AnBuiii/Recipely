package com.anbui.recipely.presentation.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.FeaturedItem
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceSmall

@Composable
fun FeaturedSection(
    modifier: Modifier
) {
    val features = listOf(
        FeaturedItem(
            title = "Asian white noodle with extra seafood",
            ownerImage = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
            ownerName = "An Bùi",
            time = "20 Min",
            image = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80"
        ),
        FeaturedItem(
            title = "Healthy Taco Salad with fresh vegetable",
            ownerImage = "https://datepsychology.com/wp-content/uploads/2022/09/gigachad.jpg",
            ownerName = "Bùi An",
            time = "12 Min",
            image = "https://images.pexels.com/photos/842571/pexels-photo-842571.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
            stringResource(R.string.featured),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = SpaceLarge)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = SpaceLarge, vertical = SpaceSmall),
            horizontalArrangement = Arrangement.spacedBy(SpaceLarge)
        ){
            items(features){
                FeaturedCard(featuredItem = it, modifier = Modifier.padding(horizontal = SpaceLarge))
            }

        }

    }
}