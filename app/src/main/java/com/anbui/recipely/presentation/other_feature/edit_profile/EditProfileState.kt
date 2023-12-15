package com.anbui.recipely.presentation.other_feature.edit_profile

import com.anbui.model.Account
import com.anbui.model.GenderType

data class EditProfileState(
    val account: com.anbui.model.Account? = null,
    val avatar: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val bio: String = "",
    val dob: Long = System.currentTimeMillis(),
    val gender: com.anbui.model.GenderType = com.anbui.model.GenderType.Male,
    val openDialog: Boolean = false,
    val success: Boolean = false
)
