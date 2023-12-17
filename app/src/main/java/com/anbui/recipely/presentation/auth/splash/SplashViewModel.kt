package com.anbui.recipely.presentation.auth.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.presentation.util.Screen
import com.anbui.recipely.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
) : ViewModel() {
    private val _loading = MutableStateFlow<String?>(null)
    val loading = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                withTimeout(Constants.SPLASH_SCREEN_DURATION) {
                    accountRepository.getCurrentAccount().first()
                    _loading.update { Screen.HomeScreen.route }
                }
            } catch (_: TimeoutCancellationException) {
                _loading.update { Screen.OnBoardingScreen.route }
            }
        }
    }


}
