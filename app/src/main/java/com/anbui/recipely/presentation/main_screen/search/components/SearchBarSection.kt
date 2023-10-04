package com.anbui.recipely.presentation.main_screen.search.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardTextField

fun LazyListScope.searchBarSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    item {
        StandardTextField(
            text = searchText, onValueChange = onSearchTextChange, leadingIcon = painterResource(
                id = R.drawable.ic_search
            ),
            hint = stringResource(id = R.string.search),
            modifier = modifier
        )
    }

}