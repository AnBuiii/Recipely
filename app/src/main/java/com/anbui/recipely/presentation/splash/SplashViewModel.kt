package com.anbui.recipely.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.data.local.account.AccountDao
import com.anbui.recipely.data.local.account.AccountEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountDao: AccountDao
) : ViewModel() {
     init {
         viewModelScope.launch {
              accountDao.insert(
                   AccountEntity(id=  "", firstName = "", lastName = "", email = "", password = "", dob = "", gender = "", avatarUrl = "", bio = "")

              )
         }
     }
}