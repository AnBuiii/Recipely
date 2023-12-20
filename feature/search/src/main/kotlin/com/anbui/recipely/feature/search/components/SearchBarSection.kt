package com.anbui.recipely.feature.search.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.core.designsystem.components.StandardTextField
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.search.R
import com.anbui.recipely.feature.search.SearchMode
import com.anbui.recipely.feature.search.SearchMode.Recipe

fun LazyListScope.searchBarSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    searchMode: SearchMode,
    onChangeMode: () -> Unit
) {
    item {
        StandardTextField(
            text = searchText,
            onValueChange = onSearchTextChange,
            leadingIcon = painterResource(
                id = R.drawable.ic_search
            ),
            hint = stringResource(id = R.string.search),
            modifier = modifier,
            trailingIcon = {
                FilledIconButton(
                    onClick = onChangeMode,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = if (searchMode == Recipe) TrueWhite else MaterialTheme.colorScheme.primary,
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fat),
                        contentDescription = "",
                        tint = if (searchMode == Recipe) MaterialTheme.colorScheme.primary else TrueWhite
                    )
                }
            }
        )
    }
}
