package com.anbui.recipely.presentation.main_screen.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.model.exampleAccounts
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.CartRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    accountRepository: AccountRepository,
    recipeRepository: RecipeRepository,
    cartRepository: CartRepository
) : ViewModel() {
    val currentAccount = accountRepository.getCurrentAccount()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            exampleAccounts[0]
        )

    val favouriteRecipes = recipeRepository.getFavouriteOfCurrentAccount()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val comingOrder = cartRepository.getAllOrderOfCurrentAccount().transform {
        Log.d("WUTT", it.toString())
        emit(it.sortedByDescending { hm -> hm.time })
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


}