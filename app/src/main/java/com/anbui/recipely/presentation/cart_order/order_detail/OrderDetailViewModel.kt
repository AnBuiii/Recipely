package com.anbui.recipely.presentation.cart_order.order_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    cartRepository: CartRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val orderId: String = checkNotNull(savedStateHandle["orderId"])

    val order = cartRepository.getOrderById(orderId = orderId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            cartRepository.getDummyOrder()
        )
}