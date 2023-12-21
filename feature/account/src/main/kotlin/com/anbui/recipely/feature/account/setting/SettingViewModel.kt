package com.anbui.recipely.feature.account.setting

import androidx.lifecycle.ViewModel
import com.anbui.recipely.core.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    suspend fun logout() {
        accountRepository.logout()
    }
}
