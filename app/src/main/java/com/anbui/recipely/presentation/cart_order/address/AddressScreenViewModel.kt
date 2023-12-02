package com.anbui.recipely.presentation.cart_order.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddressScreenState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            accountRepository.getCurrentAccount().collect { account ->
                _state.emit(
                    AddressScreenState(
                        account = account,
                        street = account.street,
                        district = account.district,
                        province = account.province,
                    )
                )

            }
        }
    }

    fun changeStreet(value: String) {
        _state.update {
            it.copy(street = value)
        }
    }

    fun changeDistrict(value: String) {
        _state.update {
            it.copy(district = value)
        }
    }

    fun changeProvince(value: String) {
        _state.update {
            it.copy(province = value)
        }
    }

    fun updateAddress() {
        viewModelScope.launch {
            _state.value.account?.let { account ->
                accountRepository.updateCurrentAccount(
                    account.copy(
                        street = _state.value.street,
                        district = _state.value.district,
                        province = _state.value.province,
                    )
                )
            }
        }
    }
}
