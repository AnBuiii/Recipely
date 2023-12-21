package com.anbui.recipely.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Account(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val bio: String,
    val avatarUrl: String,
    val dob: LocalDateTime,
    val gender: GenderType,
    val street: String,
    val district: String,
    val province: String,
) {
    fun getAddress(): String {
        return "$street, $district, $province"
    }
}

val exampleAccounts = listOf(
    Account(
        id = "exampleAccount1",
        firstName = "Ananana",
        lastName = "Bùi",
        email = "builehoaian2002@gmail.com",
        password = "123123",
        bio = "Android native developer",
        avatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=8Az4LciHuIgAX-fc5ol&_nc_ht=scontent.fdad1-1.fna&oh=00_AfC4rMm9DfZiMt3Fj412BqFtmyylN01xZayRKe88es6s-A&oe=64C25F71",
        gender = GenderType.Male,
        dob = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        street = "134 Lê Chân",
        district = "Sơn Trà",
        province = "Đà Nẵng",
    )
)
