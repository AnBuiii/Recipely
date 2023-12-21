package com.anbui.recipely.feature.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationScreenViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    val notifications = notificationRepository.getCurrentUserNotification().map {
        it.sortedByDescending {recipe ->  recipe.time }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun readNotification(id: String) {
        viewModelScope.launch {
            notificationRepository.readNotification(id)
        }
    }
}