package com.anbui.recipely.presentation.cart_order.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.exampleAccounts
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    accountRepository: AccountRepository

) : ViewModel() {
    val ingredients = cartRepository.getAllInCartOfCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    val currentAccount = accountRepository.getCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000),
        exampleAccounts[0]
    )

    private val _success = MutableStateFlow(false)
    val success = _success.asStateFlow()


    fun onChangeAmount(ingredientId: String, amount: Int) {
        viewModelScope.launch {
            cartRepository.updateAmountInCartOfCurrentAccount(ingredientId, amount)
        }
    }

    fun checkOut() {
        if (ingredients.value.isNotEmpty()) {
            viewModelScope.launch {
                cartRepository.createOrder()
                _success.emit(true)
            }
        }
    }
}
