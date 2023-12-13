package com.anbui.recipely.presentation.main_screen.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotificationScreenViewModel @Inject constructor(
    notificationRepository: NotificationRepository
) : ViewModel() {
    val notifications = notificationRepository.getCurrentUserNotification().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}