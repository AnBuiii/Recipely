package com.anbui.recipely.presentation.other_feature.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.GenderType
import com.anbui.recipely.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.TimeZone
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            with(accountRepository.getCurrentAccount().first()) {
                _uiState.update {
                    val zdt = ZonedDateTime.of(this.dob, ZoneId.systemDefault())
                    EditProfileState(
                        account = this,
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        bio = bio,
                        dob = zdt.toInstant().toEpochMilli(),
                        gender = gender,
                    )
                }
            }
        }
    }

    fun setOpenDialog(value: Boolean) {
        _uiState.update {
            it.copy(openDialog = value)
        }
    }

    fun onChangeFirstName(value: String) {
        _uiState.update {
            it.copy(firstName = value)
        }
    }

    fun onChangeLastName(value: String) {
        _uiState.update {
            it.copy(lastName = value)
        }
    }

    fun onChangeBio(value: String) {
        _uiState.update {
            it.copy(bio = value)
        }
    }

    fun onChangeDOB(value: Long) {
        _uiState.update {
            it.copy(dob = value)
        }
    }

    fun onChangeGender(value: GenderType) {
        _uiState.update {
            it.copy(gender = value)
        }
    }

    fun onUpdateProfile() {
        viewModelScope.launch {
            with(_uiState.value) {
                val dobTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dob),
                    TimeZone.getDefault().toZoneId()
                )
                this.account?.let {
                    accountRepository.updateCurrentAccount(
                        it.copy(
                            firstName = firstName,
                            lastName = lastName,
                            bio = bio,
                            dob = dobTime,
                            gender = gender,
                        )
                    )
                }
                _uiState.update { it.copy(success = true) }
            }
        }
    }
}