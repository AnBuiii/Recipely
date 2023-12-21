package com.anbui.recipely.feature.account.my_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyOrderViewModel @Inject constructor(
    cartRepository: CartRepository
) : ViewModel() {
    val currentOrders = cartRepository.getAllOrderOfCurrentAccount()
        .map {
            it.sortedByDescending { order -> order.time }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
}