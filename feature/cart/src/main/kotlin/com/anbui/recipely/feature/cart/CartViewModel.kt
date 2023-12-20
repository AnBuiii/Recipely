package com.anbui.recipely.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.data.repository.CartRepository
import com.anbui.recipely.feature.cart.address.AddressScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val accountRepository: AccountRepository

) : ViewModel() {
    val ingredients = cartRepository.getAllInCartOfCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    val currentAccount = accountRepository.getCurrentAccount()
            .onEach {
                _addressState.emit(
                    AddressScreenState(
                        street = it.street,
                        district = it.district,
                        province = it.province,
                    )
                )
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(1000),
                null
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

    private val _addressState = MutableStateFlow(AddressScreenState())
    val addressState = _addressState.asStateFlow()

    fun changeStreet(value: String) {
        _addressState.update {
            it.copy(street = value)
        }
    }

    fun changeDistrict(value: String) {
        _addressState.update {
            it.copy(district = value)
        }
    }

    fun changeProvince(value: String) {
        _addressState.update {
            it.copy(province = value)
        }
    }

    fun updateAddress() {
        viewModelScope.launch {
            with(_addressState.value){
                currentAccount.value?.let { account ->
                    accountRepository.updateAccount(
                        account.copy(
                            street = street,
                            district = district,
                            province = province
                        )
                    )
                }
            }
        }
    }
}
