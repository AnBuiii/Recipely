package com.anbui.recipely.feature.onboard.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Handle init logged id value
 *
 * TODO this function not behave correctly if the app is in background
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    accountRepository: AccountRepository,
) : ViewModel() {
    val currentAccount = accountRepository.getCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000),
        null
    )

}
