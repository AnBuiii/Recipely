package com.anbui.recipely.presentation.other_feature.edit_profile

import com.anbui.recipely.core.model.Account
import com.anbui.recipely.core.model.GenderType

data class EditProfileState(
    val account: Account? = null,
    val avatar: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val bio: String = "",
    val dob: Long = System.currentTimeMillis(),
    val gender: GenderType = GenderType.Male,
    val openDialog: Boolean = false,
    val success: Boolean = false
)
