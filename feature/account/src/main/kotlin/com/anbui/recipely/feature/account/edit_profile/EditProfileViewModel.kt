package com.anbui.recipely.feature.account.edit_profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.model.GenderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
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
                    EditProfileState(
                        account = this,
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        bio = bio,
                        dob = dob.toInstant(TimeZone.currentSystemDefault())
                            .toEpochMilliseconds(),
                        gender = gender,
                        avatar = avatarUrl
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

    fun onChangePicture(uri: Uri) {
        _uiState.update {
            it.copy(avatar = uri.toString())
        }
    }

    fun onUpdateProfile() {
        viewModelScope.launch {
            with(_uiState.value) {
                val dobTime = Instant.fromEpochMilliseconds(dob)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                this.account?.let {
                    accountRepository.updateAccount(
                        it.copy(
                            firstName = firstName,
                            lastName = lastName,
                            bio = bio,
                            dob = dobTime,
                            gender = gender,
                            avatarUrl = avatar
                        )
                    )
                }
                _uiState.update { it.copy(success = true) }
            }
        }
    }
}